import React from 'react'
import ProductCard from './ProductCard'

export default function ProductListings({products}) {
  return (
    <div className='product-listings-container'>
      <div className='product-listings-grid'>
        {/* using ternary operator */}
        {products.length > 0 ? (
          products.map((product) => (
            // parent (PL) passes product to child (PC)
            <ProductCard key={product.productId} product={product} />  
          ))
        ) : (
          <p className='product-listing-empty'>No product found</p>
        )}
      </div>
    </div>
  )
};
