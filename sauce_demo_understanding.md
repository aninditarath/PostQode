# Sauce Demo Application – System Understanding Document

**Application Name:** Sauce Demo (Swag Labs)  
**Description:** A web‑based e‑commerce demo used for UI/functional testing, showcasing product browsing, cart management, and checkout flows.  
**Target Users:** QA engineers, developers, product owners, and test automation practitioners.  
**Platform:** Web (responsive UI)  

---

## 1. Overview
- Simple e‑commerce storefront for demonstration and testing.  
- Provides a set of predefined user accounts to simulate various scenarios (standard, locked‑out, performance‑glitch, etc.).  
- No real payment processing; checkout is simulated.

## 2. Business Context
- Used by Sauce Labs to illustrate testing best practices.  
- Serves as a sandbox for training, demoing test frameworks, and validating CI pipelines.  
- Allows stakeholders to evaluate UI/UX, performance, and error handling without affecting production systems.

## 3. Target Users & Personas
| Persona | Goal | Typical Actions |
|--------|------|-----------------|
| **QA Engineer** | Validate functional and non‑functional aspects | Login with different users, add/remove items, test checkout errors |
| **Developer** | Verify UI changes & integrations | Inspect DOM, test API calls, run automated scripts |
| **Product Owner** | Review user flows & UI consistency | Navigate product list, filter/sort, complete checkout |
| **Automation Engineer** | Build and maintain test suites | Use Selenium/Playwright scripts against stable selectors |

## 4. Key Features / Modules
- **Authentication** – Multiple test users with distinct behaviors.  
- **Product Catalog** – List, sort, filter, and view product details.  
- **Shopping Cart** – Add/remove items, view cart summary.  
- **Checkout Process** – Enter information, finish order (simulated).  
- **User Menu** – Access logout, reset app state, and side navigation.  
- **Responsive Layout** – Works on desktop and mobile viewports.

## 5. User Flow / Journey
1. **Login** – Select username, enter password, click **Login**.  
2. **Browse Products** – View grid, sort (A‑Z, Z‑A, price low‑high, high‑low), filter if applicable.  
3. **Add to Cart** – Click **Add to cart** on desired items.  
4. **Open Cart** – Click cart icon, review items, modify quantity (remove).  
5. **Checkout** – Click **Checkout**, fill user info, continue, finish.  
6. **Logout / Reset** – Use side menu to log out or reset app state.

## 6. System / Architecture Overview (High‑level)
- **Front‑end:** Static HTML/CSS/JS bundle served from CDN.  
- **Back‑end (mock):** No real server; data is embedded in the client bundle.  
- **State Management:** Client‑side JavaScript stores cart & user state in memory (session).  
- **Authentication:** Simple client‑side validation against hard‑coded credentials.  
- **Error Simulation:** Certain users trigger UI errors or performance delays.

## 7. Functional Understanding of Features
- **Login:** Validates username/password; redirects to `/inventory.html`.  
- **Sorting:** Dropdown triggers client‑side array sort; updates product order.  
- **Add to Cart:** Updates in‑memory cart, changes button text to “Remove”.  
- **Cart Icon Badge:** Shows count of items in cart.  
- **Checkout Form:** Collects first name, last name, postal code; no backend verification.  
- **Finish Button:** Shows “THANK YOU FOR YOUR ORDER” screen.  
- **Side Menu:** Provides **Logout** and **Reset App State** actions.

## 8. Non‑Functional Considerations
- **Performance:** Fast static load; `performance_glitch_user` simulates latency.  
- **Accessibility:** Basic ARIA roles; missing autocomplete on inputs (noted in console).  
- **Security:** No real authentication; credentials are public.  
- **Responsiveness:** Adjusts layout for mobile widths.  
- **Reliability:** Operates offline after initial load (cached assets).

## 9. Integrations & Dependencies
- **External Libraries:** React (bundled), Redux‑like state handling.  
- **Analytics/Monitoring:** Attempts to send error reports to Backtrace (blocked in sandbox).  
- **Social Links:** Footer includes Twitter, Facebook, LinkedIn.

## 10. Assumptions
- Users have JavaScript enabled.  
- No real payment gateway; checkout is a UI flow only.  
- Test accounts are static and not subject to change.

## 11. Constraints
- No API endpoints for external integration; all data is client‑side.  
- Limited to the predefined set of usernames/passwords.  
- UI elements may change IDs/classes; rely on accessible text for selectors.

## 12. Open Questions / Clarifications
- Are there plans to expose a real backend for order persistence?  
- Should additional user roles (admin, guest) be added for broader testing?  
- Is there a need to support multi‑language/localization?  

---

*Prepared by: Senior Business Analyst & QA Lead*
