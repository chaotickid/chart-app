"use client"; // ✅ Needed in Next.js App Router

import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";

export default function TemperatureChart({ data }) {
  // Convert string numbers to real numbers
  const chartData = data.map(d => ({
    ...d,
    min: Number(d.min),
    max: Number(d.max),
    avg: Number(d.avg)
  }));

  const options = {
    chart: {
      type: "line",
      height: "400px"
    },
    title: {
      text: "Temperature Trends"
    },
    xAxis: {
      categories: chartData.map(d => d.date),
      title: { text: "Date" }
    },
    yAxis: {
      title: { text: "Temperature (°C)" }
    },
    tooltip: {
      shared: true,
      crosshairs: true
    },
    series: [
      {
        name: "Min Temp",
        data: chartData.map(d => d.min),
        color: "#8884d8"
      },
      {
        name: "Max Temp",
        data: chartData.map(d => d.max),
        color: "#82ca9d"
      },
      {
        name: "Avg Temp",
        data: chartData.map(d => d.avg),
        color: "#ffc658"
      }
    ]
  };

  return <HighchartsReact highcharts={Highcharts} options={options} />;
}
