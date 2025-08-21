'use client'
import React, { useState } from "react";
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { ScrollArea } from "@/components/ui/scroll-area";
import { Plus, Trash2 } from "lucide-react";
import axios from "axios";
// import { useRouter } from "next/router";
import { useRouter } from 'next/navigation';

const DEFAULT_DATA = {
    city: {
        name: "name_b1a05f310bb7",
        state: "state_150b6dca5344",
        country: "country_fc9c2a1c805d",
    },
    temperatureList: [
        { date: "12-02-2025", min: "10", max: "20", avg: "15" },
        { date: "12-02-2025", min: "11", max: "21", avg: "16" },
    ],
};

function toEmptySafeString(v) {
    return v ?? "";
}

const Page = () => {
    const [city, setCity] = useState({ ...DEFAULT_DATA.city });
    const [temperatureList, setTemperatureList] = useState(
        DEFAULT_DATA.temperatureList.map((t) => ({ ...t }))
    );
    const [output, setOutput] = useState("");

    const rounter = useRouter()

    const updateCity = (key, value) => {
        setCity((c) => ({ ...c, [key]: value }));
    };

    const updateTemp = (index, key, value) => {
        setTemperatureList((list) =>
            list.map((row, i) => (i === index ? { ...row, [key]: value } : row))
        );
    };

    const addTempRow = () => {
        setTemperatureList((list) => [
            ...list,
            { date: "", min: "", max: "", avg: "" },
        ]);
    };

    const removeTempRow = (index) => {
        setTemperatureList((list) => list.filter((_, i) => i !== index));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const errors = [];

        if (!city.name?.trim()) errors.push("City name is required");
        if (!city.state?.trim()) errors.push("State is required");
        if (!city.country?.trim()) errors.push("Country is required");

        temperatureList.forEach((t, idx) => {
            if (!t.date?.trim()) errors.push(`Row ${idx + 1}: date is required`);
            ["min", "max", "avg"].forEach((k) => {
                const val = t[k];
                if (val === undefined || val === null || `${val}`.trim() === "") {
                    errors.push(`Row ${idx + 1}: ${k} is required`);
                } else if (Number.isNaN(Number(val))) {
                    errors.push(`Row ${idx + 1}: ${k} must be a number`);
                }
            });
        });

        if (errors.length) {
            setOutput(JSON.stringify({ ok: false, errors }, null, 2));
            return;
        }

        const payload = {
            city: { ...city },
            temperatureList: temperatureList.map((t) => ({ ...t })),
        };
        setOutput(JSON.stringify(payload, null, 2));
        console.log("Final json: ", JSON.stringify(payload, null, 2))
    };

    const postData = () => {
        axios.post("http://localhost:8081/api/v1/add", output, {
            headers: {
                "Content-Type": "application/json"
            }
        }).then((resp) => {
            console.log(resp)
        }).catch((error) => {
            console.log(error)
        })
        rounter.push("/")
        
    }

    return (
        <div className="mx-auto max-w-4xl p-6">
            <Card className="shadow-lg">
                <CardHeader className={"mt-5"}>
                    <CardTitle className="text-2xl">City & Temperature Form</CardTitle>
                </CardHeader>
                <Separator />
                <form onSubmit={handleSubmit}>
                    <CardContent className="grid gap-6 pt-6">
                        {/* City Section */}
                        <section className="grid gap-4">
                            <h3 className="text-lg font-semibold">City</h3>
                            <div className="grid grid-cols-1 gap-4 md:grid-cols-3">
                                <div className="grid gap-2">
                                    <Label htmlFor="city-name">Name</Label>
                                    <Input
                                        id="city-name"
                                        value={toEmptySafeString(city.name)}
                                        onChange={(e) => updateCity("name", e.target.value)}
                                        placeholder="e.g., Belagavi"
                                    />
                                </div>
                                <div className="grid gap-2">
                                    <Label htmlFor="city-state">State</Label>
                                    <Input
                                        id="city-state"
                                        value={toEmptySafeString(city.state)}
                                        onChange={(e) => updateCity("state", e.target.value)}
                                        placeholder="e.g., Karnataka"
                                    />
                                </div>
                                <div className="grid gap-2">
                                    <Label htmlFor="city-country">Country</Label>
                                    <Input
                                        id="city-country"
                                        value={toEmptySafeString(city.country)}
                                        onChange={(e) => updateCity("country", e.target.value)}
                                        placeholder="e.g., India"
                                    />
                                </div>
                            </div>
                        </section>

                        <Separator />

                        {/* Temperature List Section */}
                        <section className="grid gap-4">
                            <div className="flex items-center justify-between">
                                <h3 className="text-lg font-semibold">Temperature List</h3>
                                <Button type="button" variant="secondary" onClick={addTempRow} className="rounded-2xl">
                                    <Plus className="mr-2 h-4 w-4" /> Add Row
                                </Button>
                            </div>

                            <ScrollArea className="max-h-[420px] rounded-xl border p-2">
                                <div className="grid gap-3">
                                    {temperatureList.length === 0 && (
                                        <p className="text-sm text-muted-foreground px-2 py-4">No rows. Click "Add Row"</p>
                                    )}

                                    {temperatureList.map((row, idx) => (
                                        <div
                                            key={idx}
                                            className="grid grid-cols-1 gap-3 rounded-xl border p-3 md:grid-cols-12"
                                        >
                                            <div className="md:col-span-3 grid gap-2">
                                                <Label htmlFor={`date-${idx}`}>Date (DD-MM-YYYY)</Label>
                                                <Input
                                                    id={`date-${idx}`}
                                                    value={toEmptySafeString(row.date)}
                                                    onChange={(e) => updateTemp(idx, "date", e.target.value)}
                                                    placeholder="12-02-2025"
                                                />
                                            </div>
                                            <div className="md:col-span-3 grid gap-2">
                                                <Label htmlFor={`min-${idx}`}>Min</Label>
                                                <Input
                                                    id={`min-${idx}`}
                                                    type="number"
                                                    value={toEmptySafeString(row.min)}
                                                    onChange={(e) => updateTemp(idx, "min", e.target.value)}
                                                    placeholder="10"
                                                    inputMode="numeric"
                                                />
                                            </div>
                                            <div className="md:col-span-3 grid gap-2">
                                                <Label htmlFor={`max-${idx}`}>Max</Label>
                                                <Input
                                                    id={`max-${idx}`}
                                                    type="number"
                                                    value={toEmptySafeString(row.max)}
                                                    onChange={(e) => updateTemp(idx, "max", e.target.value)}
                                                    placeholder="20"
                                                    inputMode="numeric"
                                                />
                                            </div>
                                            <div className="md:col-span-2 grid gap-2">
                                                <Label htmlFor={`avg-${idx}`}>Avg</Label>
                                                <Input
                                                    id={`avg-${idx}`}
                                                    type="number"
                                                    value={toEmptySafeString(row.avg)}
                                                    onChange={(e) => updateTemp(idx, "avg", e.target.value)}
                                                    placeholder="15"
                                                    inputMode="numeric"
                                                />
                                            </div>
                                            <div className="md:col-span-1 flex items-end justify-end">
                                                <Button
                                                    type="button"
                                                    variant="destructive"
                                                    onClick={() => removeTempRow(idx)}

                                                >
                                                    <Trash2 className="ml-5" /> Remove
                                                </Button>
                                            </div>
                                        </div>
                                    ))}
                                </div>
                            </ScrollArea>
                        </section>
                    </CardContent>

                    <CardFooter className="flex flex-col items-stretch gap-4">
                        <div className="flex items-center justify-end gap-3">
                            <Button type="submit" className="rounded-2xl">Submit</Button>
                        </div>

                        <section className="grid gap-2">
                            <Label>Output (JSON)</Label>
                            <pre className="max-h-72 overflow-auto rounded-xl border bg-muted p-4 text-sm">
                                {output || "Submit the form to see JSON here"}
                            </pre>
                        </section>
                    </CardFooter>
                </form>
                <Button variant={"outline"} className={"mt-5 hover:scale-100 transition-all"} onClick={postData} >Add city</Button>
            </Card>

        </div>
    );
};

export default Page;