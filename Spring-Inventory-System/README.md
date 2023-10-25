<h1> README </h1>



<h4> 09/03/23 </h4> 
Created style.css to run in conjunction with the Thymeleaf bootstrap
CSS URL. Edited mainscreen.html (line 14), InhousePartForm.html (line 11), OutsourcedPartForm.html (line 10),
productForm.html (line 12, 77, 78), negativeerror.html (line 8, 14, 15), confirmationaddpart.html (line 10, 15, 16), confirmationaddproduct.html (line 10, 15, 16),
confirmationassocpart.html (line 10, 15, 16), confirmationdeletepart (line 10, 15, 16),
confirmationdeleteproduct.html (line 10, 15, 16), saveproductscreen.html (line 8, 12, 13)

<h4> 09/08/23 </h4>

Created aboutscreen.html, linked to mainscreen.html from aboutscreen.html (line 69, 70), created AboutScreenController.java,
created button / link that goes from mainscreen.html to aboutscreen.html (line 25, 26)

<h4> 09/11/23 </h4>

Created parts and product objects in BootStrapData (lines 59 - 128)

<h4> 09/12/23 </h4>

Created successpage.html, errorpage.html, and BuyNowController.java. Filled out template pages for successpage and
errorpage, then created the "Buy Now" button in mainscreen.html (lines 92 - 98), linked that to the @PostMapping("/buyProduct")
in BuyNowController, decreased inventory if object searched was present (lines 27 - 36), returned / redirected either successpage or errorpage
depending on the result of checking inventory for availability (lines 38 - 51).

<h4> 09/15/23 </h4>

Added minInv and maxInv to Part.java (lines 32-36) and getter and setter methods (lines 126 - 138) , added values for minInv and maxInv for parts in
BootStrapData.java (lines 69, 70, 79, 80, 89, 90, 98, 99, 107, 108), added text input for values to InhousePartForm.html (lines 34 - 42) and OutsourcedPartForm.html (lines 34 - 42),
renamed H2 database file to second-spring-boot-h2.mv.db and changed value in application.properties (line 6),
added logic method to Part.java (lines 142, 143) to determine valid amounts, rewrote submitForm method to include inventory
logic and display potential errors in AddInhousePartController.java (lines 40-80) and AddOutsourcedPartController.java (lines 40-84), 
added file messages.properties to display error messages when user enters invalid values and attempts to submit form.

<h4> 09/18/23 </h4>

Reworked display for Thymeleaf in errorpage.html (lines 17 - 36), created additional logic in BuyNowController.java (lines 46 
-60). Tested with inventory for part / product amounts.

<h4> 09/18/23 </h4>

Created two unit tests in PartTest.java for testing minimum and maximum inventory amounts (lines 162-178).

<h4> 09/21/23 </h4>

Cleaned import statements from all classes / removed unused imports.






