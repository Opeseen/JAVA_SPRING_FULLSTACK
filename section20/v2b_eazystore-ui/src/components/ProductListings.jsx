import React, { useState, useMemo } from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import DropDown from "./DropDown";

const sortList = ["Popularity", "Price Low to High", "Price High to Low"];

export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState("Popularity");

  const filterAndSortedProduct = useMemo(() => {
    if (!Array.isArray(products)) {
      return [];
    }
    let filteredProduct = products.filter(
      (product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase()) ||
        product.description.toLowerCase().includes(searchText.toLowerCase())
    );

    return filteredProduct.slice().sort((a, b) => {
      switch (selectedSort) {
        case "Price Low to High":
          return parseFloat(a.price) - parseFloat(b.price);
        case "Price High to Low":
          return parseFloat(b.price) - parseFloat(a.price);
        case "Popularity":
        default:
          return parseInt(b.popularity) - parseInt(a.popularity);
      }
    });
  }, [products, searchText, selectedSort]);

  // define the search and sort function
  function handleSearchChange(inputSearch) {
    setSearchText(inputSearch);
  }

  function handleSortChange(sortType) {
    setSelectedSort(sortType);
  }

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-canter gap-4 pt-12">
        <SearchBox
          label={"Search"}
          placeholder={"Search products..."}
          value={searchText}
          handleSearch={(value) => handleSearchChange(value)}
        />
        <DropDown
          label={"sort by"}
          options={sortList}
          selectedValue={selectedSort}
          handleSort={(value) => handleSortChange(value)}
        />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {/* using ternary operator */}
        {filterAndSortedProduct.length > 0 ? (
          filterAndSortedProduct.map((product) => (
            // parent (PL) passes product to child (PC)
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="font-primary font-bold text-lg text-primary text-center dark:text-light">
            No product found
          </p>
        )}
      </div>
    </div>
  );
}
