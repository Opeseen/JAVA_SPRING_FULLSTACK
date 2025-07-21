import React from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import DropDown from "./DropDown";

const sortList = ["Popularity", "Price Low to High", "Price High to Low"];

export default function ProductListings({ products }) {
  let searchText = "";
  // define the search function
  function handleSearchChange(inputSearch, event) {
    searchText = inputSearch;
    console.log(searchText);
    console.log(event);
  }

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-canter gap-4 pt-12">
        <SearchBox
          label={"Search"}
          placeholder={"Search products..."}
          value={searchText}
          handleSearch={(value, event) => handleSearchChange(value, event)}
        />
        <DropDown
          label={"sort by"}
          options={sortList}
          selectedValue={"Popularity"}
        />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {/* using ternary operator */}
        {products.length > 0 ? (
          products.map((product) => (
            // parent (PL) passes product to child (PC)
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="font-primary font-bold text-lg text-primary text-center">
            No product found
          </p>
        )}
      </div>
    </div>
  );
}
