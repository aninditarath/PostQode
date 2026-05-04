# Test Cases: User Stories US-001 to US-010

## US-001: User Registration with Email Validation

### Test Case 1.1: Valid Registration
**Steps:**
1. Navigate to registration page
2. Enter valid email (e.g., test@example.com)
3. Enter password meeting complexity requirements (8+ chars, uppercase, lowercase, number, special char)
4. Confirm password
5. Click Register

**Expected Result:**
- User registration is successful
- Confirmation email is sent to provided email address
- User is shown message: "Please check your email to activate your account"
- Email contains activation link valid for 24 hours

**Pass Criteria:** Account created, confirmation email received, link works

---

### Test Case 1.2: Invalid Email Format
**Steps:**
1. Navigate to registration page
2. Enter invalid email (e.g., invalidemail, test@, @example.com)
3. Enter valid password
4. Click Register

**Expected Result:**
- Error message displays: "Please enter a valid email address"
- Registration form remains pre-filled
- No account is created
- No email sent

**Pass Criteria:** Validation error shown, no account created

---

### Test Case 1.3: Duplicate Email Registration
**Steps:**
1. Register with email: existing@example.com
2. Activate first account
3. Attempt to register again with same email
4. Enter valid password
5. Click Register

**Expected Result:**
- Error message displays: "This email is already registered"
- Second account is not created
- User is directed to login or password reset

**Pass Criteria:** Duplicate prevented, appropriate message shown

---

### Test Case 1.4: Weak Password
**Steps:**
1. Navigate to registration page
2. Enter valid email
3. Enter weak password (e.g., "pass123")
4. Click Register

**Expected Result:**
- Error message displays: "Password must be at least 8 characters and contain uppercase, lowercase, number, and special character"
- Registration is blocked
- Password requirement hints are shown

**Pass Criteria:** Weak password rejected, requirements displayed

---

### Test Case 1.5: Password Mismatch
**Steps:**
1. Navigate to registration page
2. Enter valid email
3. Enter password: TestPass123!
4. Enter confirmation password: TestPass124!
5. Click Register

**Expected Result:**
- Error message displays: "Passwords do not match"
- Registration is blocked

**Pass Criteria:** Mismatch detected, error shown

---

### Test Case 1.6: Email Activation
**Steps:**
1. Register with valid credentials
2. Check email and click activation link
3. User is redirected to login page

**Expected Result:**
- Account status changes to "Active"
- User can now login with registered credentials
- Activation link becomes invalid after click

**Pass Criteria:** Account activated, login successful

---

## US-002: User Login with Valid Credentials

### Test Case 2.1: Successful Login
**Steps:**
1. Navigate to login page
2. Enter registered email
3. Enter correct password
4. Click Login

**Expected Result:**
- User is authenticated successfully
- JWT token is generated and stored
- User is redirected to dashboard
- Session is created with 30-minute timeout
- User data is loaded in dashboard

**Pass Criteria:** Login successful, token generated, dashboard displayed

---

### Test Case 2.2: Login with Empty Email
**Steps:**
1. Navigate to login page
2. Leave email field empty
3. Enter password
4. Click Login

**Expected Result:**
- Error message displays: "Email is required"
- Login is blocked
- User remains on login page

**Pass Criteria:** Validation error shown

---

### Test Case 2.3: Login with Empty Password
**Steps:**
1. Navigate to login page
2. Enter valid email
3. Leave password field empty
4. Click Login

**Expected Result:**
- Error message displays: "Password is required"
- Login is blocked
- User remains on login page

**Pass Criteria:** Validation error shown

---

### Test Case 2.4: Session Creation
**Steps:**
1. Login with valid credentials
2. Open browser developer tools
3. Check localStorage/sessionStorage for JWT token
4. Stay logged in and monitor session

**Expected Result:**
- JWT token is stored and accessible
- Session timeout is set to 30 minutes
- User can access protected routes with token
- Token is sent with each API request

**Pass Criteria:** Token stored, session timeout set correctly

---

### Test Case 2.5: Successful Login Clears Error Messages
**Steps:**
1. Attempt login with wrong password
2. See error message
3. Enter correct credentials
4. Click Login

**Expected Result:**
- Login succeeds
- Previous error messages are cleared
- Dashboard loads
- No error messages visible on dashboard

**Pass Criteria:** Error cleared, login successful

---

## US-003: User Login Failure Handling

### Test Case 3.1: Incorrect Password
**Steps:**
1. Navigate to login page
2. Enter valid registered email
3. Enter incorrect password
4. Click Login

**Expected Result:**
- Login fails
- Error message displays: "Invalid email or password"
- Failed login attempt is logged
- User remains on login page
- User can attempt login again

**Pass Criteria:** Error shown, login blocked, counter incremented

---

### Test Case 3.2: Non-existent Email
**Steps:**
1. Navigate to login page
2. Enter non-registered email (e.g., nonexistent@example.com)
3. Enter any password
4. Click Login

**Expected Result:**
- Login fails
- Error message displays: "Invalid email or password"
- Failed login attempt is logged
- User remains on login page

**Pass Criteria:** Error shown, generic message used (for security)

---

### Test Case 3.3: Account Lock After 5 Failed Attempts
**Steps:**
1. Navigate to login page
2. Enter valid email
3. Enter incorrect password
4. Click Login (repeat 5 times)

**Expected Result:**
- First 4 attempts: Error message shown, login blocked
- 5th attempt: Error message displays: "Account locked due to too many failed login attempts. Please try again in 15 minutes."
- Account is locked for 15 minutes
- Email notification sent to user about lock

**Pass Criteria:** Account locked after 5 attempts, email sent

---

### Test Case 3.4: Login After Lock Timeout
**Steps:**
1. Account is locked from Test 3.3
2. Wait 15 minutes (or use admin override)
3. Attempt login with correct credentials

**Expected Result:**
- Account lock is released
- Login is successful
- Failed attempt counter is reset to 0

**Pass Criteria:** Lock released, login successful

---

### Test Case 3.5: Failed Attempt Counter Reset on Successful Login
**Steps:**
1. Enter incorrect password twice
2. Enter correct password on 3rd attempt
3. Login successfully

**Expected Result:**
- Login succeeds
- Failed attempt counter is reset to 0
- User can make 5 new failed attempts before lock

**Pass Criteria:** Counter reset, login successful

---

## US-005: Password Reset

### Test Case 5.1: Password Reset Request
**Steps:**
1. Navigate to login page
2. Click "Forgot Password" link
3. Enter registered email
4. Click "Send Reset Link"

**Expected Result:**
- Message displays: "If email exists, reset link has been sent"
- Email is sent to registered email address
- Email contains password reset link
- Reset link is valid for 24 hours

**Pass Criteria:** Email sent, link received

---

### Test Case 5.2: Reset Link Validity
**Steps:**
1. Request password reset
2. Receive reset link in email
3. Click reset link immediately

**Expected Result:**
- User is redirected to password reset form
- Form contains "New Password" and "Confirm Password" fields
- Page shows: "This link expires in 23 hours"

**Pass Criteria:** Link works, form displayed

---

### Test Case 5.3: Set New Password
**Steps:**
1. Click valid reset link
2. Enter new password: NewPass123!
3. Confirm new password: NewPass123!
4. Click "Reset Password"

**Expected Result:**
- Password is updated successfully
- Message displays: "Password has been reset successfully"
- User is redirected to login page
- Confirmation email sent
- Old password no longer works
- New password works for login

**Pass Criteria:** Password changed, old password invalid, new password works

---

### Test Case 5.4: Expired Reset Link
**Steps:**
1. Request password reset
2. Wait 24+ hours (or manually expire link in admin)
3. Click reset link

**Expected Result:**
- Error message displays: "This reset link has expired"
- User is redirected to "Forgot Password" page
- New reset link can be requested

**Pass Criteria:** Expired link rejected, new link available

---

### Test Case 5.5: Password Complexity on Reset
**Steps:**
1. Click valid reset link
2. Enter weak password: "password"
3. Click "Reset Password"

**Expected Result:**
- Error message displays: "Password must be at least 8 characters and contain uppercase, lowercase, number, and special character"
- Password is not updated
- User remains on reset form

**Pass Criteria:** Weak password rejected

---

### Test Case 5.6: Non-existent Email Reset Request
**Steps:**
1. Navigate to "Forgot Password" page
2. Enter non-registered email: nonexistent@example.com
3. Click "Send Reset Link"

**Expected Result:**
- Message displays: "If email exists, reset link has been sent"
- No email is actually sent (for security)
- User cannot tell if email exists

**Pass Criteria:** Generic message shown, no email sent

---

## US-006: Multi-Factor Authentication Setup

### Test Case 6.1: Enable TOTP MFA
**Steps:**
1. Login to account
2. Navigate to Settings > Security
3. Click "Enable Multi-Factor Authentication"
4. Select "Authenticator App (TOTP)"
5. Scan QR code with Google Authenticator/Microsoft Authenticator
6. Enter 6-digit code from app
7. Click "Verify and Enable"

**Expected Result:**
- MFA is enabled on account
- 8-10 backup codes are generated and displayed
- Message displays: "Multi-Factor Authentication enabled successfully"
- Backup codes can be downloaded/printed
- MFA is required on next login

**Pass Criteria:** MFA enabled, backup codes provided

---

### Test Case 6.2: Generate Backup Codes
**Steps:**
1. MFA is enabled on account
2. Navigate to Settings > Security
3. Click "Generate New Backup Codes"
4. Click "Generate"

**Expected Result:**
- Previous backup codes are invalidated
- 8-10 new backup codes are generated
- Codes are displayed for download/copy
- Confirmation email sent

**Pass Criteria:** New codes generated, old codes invalid

---

### Test Case 6.3: Login with MFA - Valid Code
**Steps:**
1. Account has TOTP MFA enabled
2. Navigate to login page
3. Enter email and password correctly
4. MFA prompt appears asking for 6-digit code
5. Enter valid 6-digit code from authenticator app
6. Click "Verify"

**Expected Result:**
- Code is accepted
- User is authenticated
- User is redirected to dashboard
- Session is created

**Pass Criteria:** Login successful with MFA

---

### Test Case 6.4: Login with MFA - Invalid Code
**Steps:**
1. Account has TOTP MFA enabled
2. Enter email and password correctly
3. MFA prompt appears
4. Enter invalid/incorrect 6-digit code
5. Click "Verify"

**Expected Result:**
- Error message displays: "Invalid authentication code"
- User remains on MFA prompt
- User can attempt again
- Code expires after 30 seconds

**Pass Criteria:** Invalid code rejected

---

### Test Case 6.5: Login with Backup Code
**Steps:**
1. Account has TOTP MFA enabled
2. Enter email and password correctly
3. MFA prompt appears
4. Click "Use backup code instead"
5. Enter one of the backup codes
6. Click "Verify"

**Expected Result:**
- Backup code is accepted
- User is authenticated
- Backup code is marked as used
- Cannot use same code again
- Login is successful

**Pass Criteria:** Backup code works, marked as used

---

### Test Case 6.6: Disable MFA
**Steps:**
1. Navigate to Settings > Security
2. Click "Disable Multi-Factor Authentication"
3. Enter current password for confirmation
4. Click "Disable"

**Expected Result:**
- MFA is disabled
- Message displays: "Multi-Factor Authentication has been disabled"
- MFA is no longer required on login
- Backup codes are invalidated
- Confirmation email sent

**Pass Criteria:** MFA disabled, password confirmed

---

## US-007: User Profile Management

### Test Case 7.1: Update User Name
**Steps:**
1. Login to account
2. Navigate to Settings > Profile
3. Click "Edit Profile"
4. Update Name field: "John Doe"
5. Click "Save Changes"

**Expected Result:**
- Name is updated successfully
- Message displays: "Profile updated successfully"
- Updated name appears in account settings
- Updated name appears in dashboard header

**Pass Criteria:** Name updated, displayed in UI

---

### Test Case 7.2: Update Email Address
**Steps:**
1. Navigate to Settings > Profile
2. Click "Edit Profile"
3. Change email to: newemail@example.com
4. Verification code is sent to old email
5. Enter verification code
6. Click "Confirm New Email"

**Expected Result:**
- Verification email sent to old address
- New email is in pending status
- After verification, new email is active
- Can login with new email
- Old email access is disabled

**Pass Criteria:** Email change verified, new email active

---

### Test Case 7.3: Update Phone Number
**Steps:**
1. Navigate to Settings > Profile
2. Click "Edit Profile"
3. Enter phone number: +1-234-567-8900
4. Click "Save Changes"

**Expected Result:**
- Phone number is updated and validated
- Format is preserved correctly
- Message displays: "Profile updated successfully"
- Phone number is visible in account settings

**Pass Criteria:** Phone number saved and validated

---

### Test Case 7.4: Upload Profile Picture
**Steps:**
1. Navigate to Settings > Profile
2. Click "Upload Profile Picture"
3. Select image file (JPG/PNG, max 5MB)
4. Click "Upload"

**Expected Result:**
- Image is uploaded successfully
- Image is displayed in profile
- Previous profile picture is replaced
- Image appears in dashboard header
- File size is validated (max 5MB)

**Pass Criteria:** Image uploaded, displayed, size validated

---

### Test Case 7.5: Upload Picture Exceeding Size Limit
**Steps:**
1. Navigate to Settings > Profile
2. Click "Upload Profile Picture"
3. Select image file exceeding 5MB
4. Click "Upload"

**Expected Result:**
- Upload is rejected
- Error message displays: "Image size must not exceed 5MB"
- Previous picture remains unchanged

**Pass Criteria:** Size limit enforced

---

### Test Case 7.6: Change Password with Verification
**Steps:**
1. Navigate to Settings > Profile
2. Click "Change Password"
3. Enter current password
4. Enter new password: NewSecurePass123!
5. Confirm new password: NewSecurePass123!
6. Click "Change Password"

**Expected Result:**
- Password is changed successfully
- Message displays: "Password changed successfully"
- Confirmation email sent
- Old password no longer works
- New password works for login

**Pass Criteria:** Password changed, verification email sent

---

### Test Case 7.7: Change Password - Incorrect Current Password
**Steps:**
1. Navigate to Settings > Change Password
2. Enter incorrect current password
3. Enter new password
4. Click "Change Password"

**Expected Result:**
- Error message displays: "Current password is incorrect"
- Password is not changed
- User remains on change password form

**Pass Criteria:** Verification failed

---

### Test Case 7.8: View Account Activity Log
**Steps:**
1. Navigate to Settings > Account Activity
2. Review login history and account changes

**Expected Result:**
- Activity log displays all recent logins
- Each entry shows: timestamp, IP address, device/browser, location
- Suspicious activity can be flagged
- User can logout other sessions
- Clear list of account security events

**Pass Criteria:** Activity log visible, detailed entries shown

---

### Test Case 7.9: Manage Notification Preferences
**Steps:**
1. Navigate to Settings > Notifications
2. Toggle notification preferences:
   - Email notifications for orders: ON
   - Password change alerts: ON
   - Marketing emails: OFF
3. Click "Save Preferences"

**Expected Result:**
- Preferences are saved
- Message displays: "Notification preferences updated"
- User receives/does not receive emails based on settings
- Settings persist on re-login

**Pass Criteria:** Preferences saved and enforced

---

## US-008: API Authentication

### Test Case 8.1: Valid JWT Token in API Request
**Steps:**
1. Login to application
2. Obtain JWT token from browser storage
3. Make API request with header: Authorization: Bearer {JWT_TOKEN}
4. Example: GET /api/products

**Expected Result:**
- API request is accepted (200 OK)
- Response returns valid product data
- Token is recognized as valid
- Response body matches expected structure

**Pass Criteria:** API accepts valid token, returns 200 OK

---

### Test Case 8.2: Missing Authorization Header
**Steps:**
1. Make API request without Authorization header
2. Example: GET /api/products

**Expected Result:**
- Request is rejected
- Response status: 401 Unauthorized
- Error message displays: "Authorization header missing"
- No data is returned

**Pass Criteria:** 401 returned, request blocked

---

### Test Case 8.3: Invalid JWT Token
**Steps:**
1. Make API request with malformed token: Authorization: Bearer invalid_token_xyz
2. Example: GET /api/products

**Expected Result:**
- Request is rejected
- Response status: 401 Unauthorized
- Error message displays: "Invalid or malformed token"
- No data is returned

**Pass Criteria:** 401 returned, invalid token rejected

---

### Test Case 8.4: Expired JWT Token
**Steps:**
1. Use an expired JWT token (if testing with old token)
2. Make API request with expired token
3. Example: GET /api/products

**Expected Result:**
- Request is rejected
- Response status: 401 Unauthorized
- Error message displays: "Token has expired"
- User can refresh token or must re-login

**Pass Criteria:** 401 returned, expired token rejected

---

### Test Case 8.5: Token Refresh Mechanism
**Steps:**
1. Login to application
2. Token is about to expire (less than 5 minutes remaining)
3. Make API request that triggers token refresh
4. Check new token in response headers

**Expected Result:**
- Token is automatically refreshed
- New JWT token is issued
- Old token becomes invalid
- User session continues without interruption
- Refresh token in response header: X-New-Token

**Pass Criteria:** Token refreshed automatically

---

### Test Case 8.6: API Response Matches UI
**Steps:**
1. Login to application and navigate to product page
2. Observe products displayed in UI
3. Make same API request: GET /api/products with JWT token
4. Compare response data with UI display

**Expected Result:**
- API response data matches UI display
- Product information is identical
- Response structure is consistent
- Pricing, images, descriptions match

**Pass Criteria:** API and UI data consistent

---

### Test Case 8.7: Protected Endpoint Authorization
**Steps:**
1. Attempt to access protected endpoint: POST /api/orders
2. First without token (should fail)
3. Then with valid token (should succeed)
4. Then with token from different user (should fail for that user's data)

**Expected Result:**
- Without token: 401 Unauthorized
- With valid token: 200 OK / 201 Created
- With different user token: 403 Forbidden (cannot access other user's data)
- Each user can only access their own resources

**Pass Criteria:** Authorization properly enforced per user

---

## US-009: View Product List

### Test Case 9.1: Product List Loads
**Steps:**
1. Navigate to Products page
2. Wait for page to load

**Expected Result:**
- Product list loads within 2 seconds
- Products are displayed in grid/list view
- Each product shows: image, name, price, rating
- No duplicate products are shown
- Page is responsive

**Pass Criteria:** Page loads in <2 seconds, products displayed

---

### Test Case 9.2: Pagination - 10 Items Per Page
**Steps:**
1. Navigate to Products page
2. Set pagination to "10 items per page"
3. Observe displayed products
4. Navigate to next page

**Expected Result:**
- 10 products displayed on first page
- Page 2 shows next 10 products
- Page navigation works correctly
- Total number of pages displayed
- Can navigate backwards

**Pass Criteria:** Pagination works, 10 items shown

---

### Test Case 9.3: Pagination - 25 Items Per Page
**Steps:**
1. Navigate to Products page
2. Set pagination to "25 items per page"
3. Observe page changes

**Expected Result:**
- 25 products displayed per page
- Fewer total pages compared to 10 items/page
- Previous selection is remembered

**Pass Criteria:** Page size updated, correct items shown

---

### Test Case 9.4: Pagination - 50 Items Per Page
**Steps:**
1. Navigate to Products page
2. Set pagination to "50 items per page"

**Expected Result:**
- 50 products displayed per page
- Load time remains acceptable (<3 seconds)
- Page responsiveness is maintained

**Pass Criteria:** 50 items loaded, performance acceptable

---

### Test Case 9.5: Default Sort by Popularity
**Steps:**
1. Navigate to Products page
2. Observe sort order of products

**Expected Result:**
- Products are sorted by popularity (highest first)
- Sort option shows "Popularity" as selected
- Products with highest ratings/sales appear first
- Consistent sorting across all pages

**Pass Criteria:** Sorted by popularity by default

---

### Test Case 9.6: Product Information Display
**Steps:**
1. Navigate to Products page
2. Observe each product card

**Expected Result:**
- Product image displays clearly
- Product name is visible and clickable
- Price is displayed in correct currency
- Rating (stars) is visible
- No overlapping text
- Responsive layout on different screen sizes

**Pass Criteria:** All product info visible, no layout issues

---

### Test Case 9.7: Load Time Performance
**Steps:**
1. Open Products page (first load)
2. Check browser network tab for load time
3. Navigate through different pagination pages

**Expected Result:**
- Initial load: <2 seconds
- Page navigation: <1 second
- Images load progressively
- No content shifts after loading

**Pass Criteria:** Performance meets requirements

---

### Test Case 9.8: No Duplicate Products
**Steps:**
1. Navigate through all pages of product list
2. Note product IDs/names
3. Check for any duplicate entries

**Expected Result:**
- No products appear twice
- Each product ID is unique
- Product count is accurate
- No missing products

**Pass Criteria:** No duplicates found, accurate count

---

## US-010: Search Products by Name

## US-001: Product Search

### Test Case 1.1: Search by Exact Product Name
**Steps:**
1. Navigate to the Products page.
2. Enter the exact product name “Apple iPhone 14” in the search bar.
3. Press **Enter** or click the **Search** button.

**Expected Result:**
- The product list displays only items whose name exactly matches “Apple iPhone 14”.
- The matching product appears at the top of the results.
- Result count reflects the number of exact matches (usually 1).

**Pass Criteria:** Exact name returns the correct product with no unrelated items.

### Test Case 1.2: Search with Category Filter
**Steps:**
1. Navigate to the Products page.
2. Enter “Running Shoes” in the search bar.
3. Select the category filter “Footwear”.
4. Click **Search**.

**Expected Result:**
- Results are limited to products named “Running Shoes” within the “Footwear” category.
- No products from other categories appear.
- Filter badge shows “Footwear” is active.

**Pass Criteria:** Category filter correctly narrows search results.

### Test Case 1.3: No Results Handling
**Steps:**
1. Navigate to the Products page.
2. Enter a non‑existent product name “XYZ123Nonexistent”.
3. Click **Search**.

**Expected Result:**
- A friendly “No products found” message is displayed.
- Suggestions such as “Check spelling” or “Try a different keyword” are shown.
- The search box retains the entered term.

**Pass Criteria:** No‑result scenario is handled gracefully.

### Test Case 1.4: Search Performance
**Steps:**
1. Navigate to the Products page.
2. Enter a common term “Phone”.
3. Measure the time from clicking **Search** to results appearing.

**Expected Result:**
- Results are displayed within 1 second.
- A loading indicator appears briefly and disappears once results load.

**Pass Criteria:** Search response time ≤ 1 second.

### Test Case 1.5: Case‑Insensitive Search
**Steps:**
1. Perform three searches for the term “sOnY” using different capitalizations:
   - “sony”
   - “SONY”
   - “SoNy”
2. Click **Search** each time.

**Expected Result:**
- All three searches return the same set of Sony products.
- No case‑sensitivity issues are observed.

**Pass Criteria:** Search is case‑insensitive.

## US-002: Add Product to Cart

### Test Case 2.1: Add Single Item with Default Quantity
**Steps:**
1. Navigate to a product detail page (e.g., “Canon EOS R5”).
2. Click **Add to Cart** without changing the quantity selector.

**Expected Result:**
- The cart badge increments by 1.
- The cart preview shows the product with quantity 1.
- Stock level is reduced accordingly.

**Pass Criteria:** Item added with default quantity 1.

### Test Case 2.2: Add Multiple Quantity
**Steps:**
1. Open a product detail page.
2. Change the quantity selector to 3.
3. Click **Add to Cart**.

**Expected Result:**
- Cart badge increments by 3.
- Cart preview lists the product with quantity 3.
- Total price reflects 3 × unit price.

**Pass Criteria:** Correct quantity and pricing applied.

### Test Case 2.3: Add Out‑of‑Stock Item
**Steps:**
1. Locate a product marked “Out of Stock”.
2. Attempt to click **Add to Cart**.

**Expected Result:**
- The **Add to Cart** button is disabled or shows a tooltip “Out of stock”.
- No change to cart badge.
- An informational message is displayed.

**Pass Criteria:** Out‑of‑stock items cannot be added.

### Test Case 2.4: Cart Persistence After Logout
**Steps:**
1. Add a product to the cart.
2. Log out of the application.
3. Log back in.

**Expected Result:**
- The previously added item remains in the cart.
- Cart badge shows the correct quantity.
- No loss of cart data.

**Pass Criteria:** Cart persists across sessions.

### Test Case 2.5: Cart Update – Remove Item
**Steps:**
1. Add a product to the cart.
2. Open the cart page.
3. Click the **Remove** (trash) icon for the item.

**Expected Result:**
- The item disappears from the cart list.
- Cart badge decrements accordingly.
- Total price updates to reflect removal.

**Pass Criteria:** Item removal works correctly.

## US-003: Manage Shipping Address

### Test Case 3.1: Add New Shipping Address
**Steps:**
1. Log in and navigate to **Account > Shipping Addresses**.
2. Click **Add New Address**.
3. Fill in all required fields (Name, Street, City, State, ZIP, Country, Phone).
4. Click **Save**.

**Expected Result:**
- The new address appears in the address list.
- A success toast “Address added successfully” is shown.
- The address can be selected during checkout.

**Pass Criteria:** New address saved and selectable.

### Test Case 3.2: Edit Existing Address
**Steps:**
1. From the address list, select an existing address and click **Edit**.
2. Modify the street name.
3. Click **Save**.

**Expected Result:**
- The address list reflects the updated street.
- A success message is displayed.
- No duplicate address entries are created.

**Pass Criteria:** Address updates correctly.

### Test Case 3.3: Delete Shipping Address
**Steps:**
1. Choose an address from the list.
2. Click **Delete** and confirm the prompt.

**Expected Result:**
- The address is removed from the list.
- A confirmation toast “Address deleted” appears.
- If the deleted address was the default, a new default is set automatically.

**Pass Criteria:** Address deletion works and default handling is correct.

### Test Case 3.4: Set Default Shipping Address
**Steps:**
1. In the address list, select an address and click **Set as Default**.

**Expected Result:**
- The chosen address is marked with a “Default” label.
- During checkout, the default address is pre‑selected.
- Only one address is marked as default at any time.

**Pass Criteria:** Default address functionality works.

### Test Case 3.5: Validation Errors on Incomplete Form
**Steps:**
1. Open **Add New Address** form.
2. Leave the ZIP code field empty and attempt to **Save**.

**Expected Result:**
- Inline validation error “ZIP code is required” appears.
- Form is not submitted.
- All other valid fields retain entered data.

**Pass Criteria:** Form validation prevents incomplete address creation.

## US-005: View Order History

### Test Case 5.1: Order History List Displays Correctly
**Steps:**
1. Log in and navigate to **My Orders**.
2. Observe the list of past orders.

**Expected Result:**
- Each order row shows Order ID, Date, Status, Total Amount.
- Orders are sorted by most recent date first.
- Pagination works if more than 10 orders.

**Pass Criteria:** Order list displays accurate information.

### Test Case 5.2: View Order Details
**Steps:**
1. From the order list, click on an Order ID.

**Expected Result:**
- Order detail page shows items, quantities, prices, shipping address, and payment method.
- Order status timeline is displayed (e.g., Placed → Shipped → Delivered).

**Pass Criteria:** Detailed view matches the order data.

### Test Case 5.3: Reorder from Order History
**Steps:**
1. Open a past order’s detail page.
2. Click **Reorder** button.

**Expected Result:**
- All items from the past order are added to the cart with the same quantities.
- Cart badge updates accordingly.
- User can proceed to checkout with these items.

**Pass Criteria:** Reorder functionality populates cart correctly.

### Test Case 5.4: Filter Order History by Date Range
**Steps:**
1. In **My Orders**, set a date filter from “01‑Jan‑2023” to “31‑Dec‑2023”.
2. Apply the filter.

**Expected Result:**
- Only orders placed within the selected range are displayed.
- Filter badge shows the active date range.

**Pass Criteria:** Date filtering works as expected.

### Test Case 5.5: Export Order History
**Steps:**
1. On the **My Orders** page, click **Export** and choose “CSV”.

**Expected Result:**
- A CSV file downloads containing Order ID, Date, Status, Total, and Item details.
- File format adheres to CSV standards and can be opened in spreadsheet software.

**Pass Criteria:** Export generates a correct CSV file.


### Test Case 10.1: Search by Exact Product Name
**Steps:**
1. Navigate to Products page
2. Enter search term: "Samsung Galaxy"
3. Click Search or press Enter

**Expected Result:**
- Search returns products matching "Samsung Galaxy"
- Results display within 1 second
- Exact matches appear first
- Number of results displayed
- Can view all matching products

**Pass Criteria:** Search returns relevant results in <1 second

---

### Test Case 10.2: Search with Partial Name
**Steps:**
1. Navigate to Products page
2. Enter search term: "Galaxy"
3. Press Enter

**Expected Result:**
- All products containing "Galaxy" are returned
- Multiple results shown (if exist)
- Results relevant to search term
- Results within 1 second

**Pass Criteria:** Partial match works, results displayed

---

### Test Case 10.3: Search with Typo - Fuzzy Matching
**Steps:**
1. Navigate to Products page
2. Enter search term: "Samsng" (missing 'u')
3. Press Enter

**Expected Result:**
- Search suggests: "Did you mean: Samsung?"
- Results for correct spelling shown
- Fuzzy matching finds close matches
- Helpful suggestions provided

**Pass Criteria:** Typo handled, suggestions shown

---

### Test Case 10.4: Search History - Logged In User
**Steps:**
1. Login to account
2. Search for: "Laptop"
3. Search for: "Phone"
4. Search for: "Tablet"
5. Navigate to search box

**Expected Result:**
- Recent searches are shown in dropdown: "Laptop", "Phone", "Tablet"
- Can click recent search to repeat
- History is personalized per user
- Search history persists across sessions

**Pass Criteria:** Search history saved and displayed

---

### Test Case 10.5: Search History - Anonymous User
**Steps:**
1. Do not login
2. Perform searches for: "Laptop", "Phone"
3. Refresh page or navigate away
4. Return to search box

**Expected Result:**
- Search history may show in session only (not persisted)
- OR history shows but requires login to save
- Message indicates: "Login to save search history"

**Pass Criteria:** Anonymous search handled appropriately

---

### Test Case 10.6: Clear Search History
**Steps:**
1. Login to account
2. View search history
3. Click "Clear Search History"
4. Confirm clearing

**Expected Result:**
- All search history is deleted
- Search dropdown no longer shows previous searches
- History cleared for all devices
- Confirmation message displayed

**Pass Criteria:** History cleared successfully

---

### Test Case 10.7: No Results Page
**Steps:**
1. Navigate to Products page
2. Enter search term: "NonexistentProductXYZ123"
3. Press Enter

**Expected Result:**
- Page displays: "No products found for 'NonexistentProductXYZ123'"
- Suggestions shown: "Did you mean...", "Popular searches"
- Link to browse all products
- Search box remains visible for new search

**Pass Criteria:** No results handled gracefully

---

### Test Case 10.8: Search Response Time
**Steps:**
1. Navigate to search box
2. Enter search term
3. Measure time for results to appear

**Expected Result:**
- Search results appear within 1 second
- Loading indicator shows during search
- Results update smoothly without page reload
- No lag or delay in display

**Pass Criteria:** Results appear in <1 second

---

### Test Case 10.9: Search Across Product Categories
**Steps:**
1. Search for a general term: "red"
2. Check results include products from multiple categories

**Expected Result:**
- Results include clothing, electronics, accessories (if term matches)
- Category labels shown for each result
- Can filter results by category
- Comprehensive search across all categories

**Pass Criteria:** Search covers all categories

---

### Test Case 10.10: Case-Insensitive Search
**Steps:**
1. Search for: "SAMSUNG"
2. Search for: "samsung"
3. Search for: "SaMsUnG"

**Expected Result:**
- All searches return identical results
- Results are case-insensitive
- Display is consistent regardless of input case

**Pass Criteria:** Case-insensitive search working

---

## Summary of Test Cases

- **US-001**: 6 test cases (registration validation, email, password, activation)
- **US-002**: 5 test cases (successful login, validation, sessions)
- **US-003**: 5 test cases (failure handling, account locking, attempt tracking)
- **US-005**: 6 test cases (reset request, link validity, expiration, complexity)
- **US-006**: 6 test cases (TOTP setup, backup codes, MFA login, disable)
- **US-007**: 9 test cases (profile updates, picture upload, password change, activity)
- **US-008**: 7 test cases (JWT validation, expiration, refresh, authorization)
- **US-009**: 8 test cases (product list, pagination, sorting, performance)
- **US-010**: 10 test cases (search, autocomplete, fuzzy matching, history)

**Total: 62 comprehensive test cases**

---

## Test Execution Notes

- Execute test cases in order within each user story
- Document actual results vs. expected results
- Report any deviations as bugs
- Re-test after bug fixes
- Verify on multiple browsers (Chrome, Firefox, Safari)
- Test on mobile and desktop views
- Performance testing should use network throttling (3G/4G)
