# Shopping Cart Implementation – Code Improvements

## Overview
This implementation improves the original shopping cart code by enhancing **readability, maintainability, and correctness**, while preserving the existing business functionality: adding items to a cart and calculating totals.

---

## Approach
After reviewing the exercise instructions, I examined the code for any redundant or repititive logic that could be removed or refactored without changing the main functionality. I specifically looked for areas where the code violated the DRY (Don't Repeat Yourself) principle.

### Issues Found
1. The BigDecimal import was unused, which could confuse developers working on the codebase.
2. In `addItem`, each request parameter is extracted individually, causing multiple @RequestParam annotations when posting new item information.
3. The logic for calculating the cart total is duplicated in both addItem and getTotal, violating DRY principles.

---

## Key Improvements

### 1. Use of a DTO (`AddItemRequestDTO`)
- Consolidates all request parameters for adding an item into a single object.
- Improves method readability and maintainability.
- Makes it easier to extend in the future if new fields (e.g., discounts, categories) are added.

### 2. Private Helper Method (`calculateTotal`)
- Centralises the logic for calculating the cart total.
- Eliminates duplicate code in both `addItem()` and `getTotal()`.
- Simplifies future updates to total calculation (e.g., taxes, discounts) by having a single point of change.

### 3. Cleaner Controller Methods
- `addItem()` clearly separates adding/updating items and calculating totals.
- `getTotal()` is concise, using the same helper method for calculation.
- Enhances maintainability and reduces the risk of errors.

### 4. Modern Coding Practices
- Removed unused imports for cleaner code.
- Used Lombok annotations (`@Getter` / `@Setter`) to reduce boilerplate in the DTO.
- Maintains proper encapsulation and separation of concerns.

---

## Summary
The refactored code is **cleaner, easier to read, and more maintainable**, while still performing the same business operations as the original version: adding items to a shopping cart and calculating totals.
