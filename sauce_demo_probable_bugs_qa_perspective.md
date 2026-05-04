# SauceDemo – Top 30 Probable Bugs (Context-Free Testing Approach)

## Objective
Identify **high-probability bugs** in SauceDemo using a **context-free testing mindset** (data integrity, consistency, state handling, usability, security, and system behavior across UI + API layers).

---

## 🔐 1. Authentication & Access Bugs

1. Login succeeds with empty username/password (missing validation)
2. Incorrect or generic error message for invalid login
3. Locked user (`locked_out_user`) intermittently allowed to login
4. Session persists after logout (back button/session reuse)
5. Multiple concurrent sessions not handled (login from two tabs/devices)
6. Session does not expire after inactivity (no timeout handling)

---

## 🛍️ 2. Inventory & Product Bugs

7. Product sorting (price/name) not applied correctly
8. Sorting UI changes but underlying data order does not
9. Duplicate or missing products in list
10. Product images broken or mismatched with product names
11. Add to Cart button not toggling to "Remove" correctly
12. Product details inconsistent between list and cart

---

## 🛒 3. Cart State & Data Integrity Bugs

13. Cart badge count incorrect after multiple operations
14. Removed item still visible after refresh/navigation
15. Duplicate entries instead of quantity increment
16. Cart state lost on refresh or navigation
17. Cart not cleared after "Reset App State"
18. Items added in one tab not reflected in another tab (sync issue)

---

## 🔄 4. Workflow & Navigation Bugs

19. User can bypass checkout steps via URL manipulation
20. Back navigation corrupts state (e.g., checkout resets)
21. Checkout proceeds even with missing mandatory fields
22. Navigation menu actions (logout/reset) behave inconsistently
23. Deep links (direct URLs) allow access without login

---

## 💳 5. Checkout & Data Validation Bugs

24. Invalid zip code or special characters accepted
25. Total price calculation mismatch (sum vs displayed total)
26. Tax calculation inconsistent or incorrect
27. Order confirmation displayed without completing steps
28. User details not retained correctly between steps

---

## 🔌 6. API & Backend Behavior Bugs (Inferred)

29. API response mismatch with UI (data inconsistency)
30. Add-to-cart API succeeds but UI not updated (sync issue)
31. API returns stale product data (caching issue)
32. Missing or incorrect error codes for failed requests

---

## ⚡ 7. Performance & UI Bugs

33. Slow page load for `performance_glitch_user`
34. UI misalignment or broken layout (`problem_user`, `visual_user`)
35. Buttons clickable multiple times causing duplicate actions
36. Loading indicators missing or inconsistent

---

## 🧪 8. Data & Testability Bugs

37. Test data (users/products) not reset between sessions
38. Inconsistent behavior across predefined users
39. Hardcoded data causing incorrect assumptions in tests
40. Lack of logging/visibility for failures (hard to debug)

---

## 🧠 Key Insight

These bugs are derived from **context-free testing questions such as**:
- What happens with invalid or unexpected input?
- Is data consistent across UI, API, and navigation?
- Does state persist correctly across flows?
- Can workflows be bypassed or broken?
- Does the system behave consistently under different users/conditions?

👉 The goal is to **challenge assumptions, not just validate flows**.

---

## 🚀 Final Thought

A strong QA engineer:
- Questions data integrity
- Breaks workflows intentionally
- Validates system behavior under stress and edge cases

This list represents **SDET-level bug thinking across UI, API, state, and system design**.