import React from "react";

export default function DropDown({
  label,
  options,
  selectedValue,
  handleSort,
}) {
  return (
    <div className="flex items-center gap-2 justify-end flex-1 font-primary">
      <label className="text-lg font-semibold text-primary">{label}</label>
      <select
        className="px-3 py-2 text-base border rounded-md transition border-primary focus:ring focus:ring-dark focus:outline-none text-gray-900"
        value={selectedValue}
        onChange={(event) => handleSort(event.target.value)}
      >
        {options.map((optionValue, index) => (
          <option key={index} value={optionValue}>
            {optionValue}
          </option>
        ))}
      </select>
    </div>
  );
}
