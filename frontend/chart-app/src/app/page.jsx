'use client'
import Image from "next/image";
import TemperatureChart from "./_components/TemperatureChart";
import { useState } from "react";
import axios from "axios";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { toast, ToastContainer } from "react-toastify";

const cityData = {
  city: {
    name: "name_b1a05f310bb7",
    state: "state_150b6dca5344",
    country: "country_fc9c2a1c805d"
  },
  temperatureList: [
    { date: "12-02-2025", min: "10", max: "20", avg: "15" },
    { date: "13-02-2025", min: "11", max: "21", avg: "16" },
    { date: "14-02-2025", min: "12", max: "22", avg: "17" }
  ]
};

export default function Home() {

  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");

  const [cityData, updateCityData] = useState(null);

  // useEffect(()=>{

  //     get()
  // }, [])

  const get = () => {
    axios.get("http://localhost:8081/api/v1/get", {
      headers: {
        "Content-Type": "application/json"
      }
    }).then((resp) => {
      console.log("Get resp data: " + resp)
    }).catch((error) => {
      console.log(error)
    })
  }

  const handleShowData = () => {
    console.log("City:", city);
    console.log("State:", state);
    console.log("Country:", country);

    // Example: you can send to API or filter chart here
    axios.get("http://localhost:8081/api/v1/get", {
      params: { city, state, country }, // ✅ axios automatically builds query string
      headers: { "Content-Type": "application/json" }
    })
      .then((resp) => {
        console.log("Get resp data: ", resp.data);
        updateCityData(resp.data)
        toast.success("Fetched data successfully")
        // 🔊 play error sound
        const audio = new Audio("/success.mp3");
        audio.play().catch((err) => console.warn("Audio play failed:", err));
      })
      .catch((error) => {
        console.error("Error:", error);
        toast.error(`Failed to fetch city ❌ ${error.code}`);
        // 🔊 play error sound
        const audio = new Audio("/error.mp3");
        audio.play().catch((err) => console.warn("Audio play failed:", err));
      });
  };

  return (

    <div className="p-6">
      <Input
        placeholder="City"
        value={city}
        onChange={(e) => setCity(e.target.value)}
      />
      <Input
        placeholder="State"
        className="mt-5"
        value={state}
        onChange={(e) => setState(e.target.value)}
      />
      <Input
        placeholder="Country"
        className="mt-5"
        value={country}
        onChange={(e) => setCountry(e.target.value)}
      />

      <Button
        variant="outline"
        className="mt-5"
        onClick={handleShowData}
      >
        Show Data
      </Button>

      {cityData != null ? <>
        <h1 className="text-xl font-bold mb-4 mt-5">
          {cityData.city.name} Temperature Trend
        </h1>
        <TemperatureChart data={cityData.temperatureList} />

      </> : <></>}

      <ToastContainer></ToastContainer>
    </div>
  );
}
