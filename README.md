# Accounting-Ledger
This is a Java CLI applicaion specifically for an accounting ledger. 
It's designed to help you efficiently manage and keep track of your financial transactions. 
It allows you to record deposits and payments, view a ledger of all your financial activities, 
and run various financial reports to see how much you have!
## This CLI app has the following: 
1. The Home Screen which prompts the user with options to Add Deposit, Make Payments, display the Ledger screen, or Exit the application.
   
![HomeScreen](https://github.com/AlexisS05/accounting-ledger/assets/57822868/911bb99d-b35d-423c-97a9-3d874d64a1dc)

2. The Ledger screen displays all entries with the newest entries first and allows the user to filter entries by All, Deposits, Payments, or Reports.
   
![LedgerMenu](https://github.com/AlexisS05/accounting-ledger/assets/57822868/3fd7c9c8-c60e-44d4-8a46-180fbbebe48e)

3. The Reports screen offers pre-defined reports, including Month to Date, Previous Month, Year to Date, Previous Year, and Search by Vendor. If the user enters a value for a search field, the application filters on that field.
   
![ReportsMenu](https://github.com/AlexisS05/accounting-ledger/assets/57822868/e378d7b7-5ede-4a46-be37-e515eda56df3)

4. The Custom Search feature, a challenge option inside the Reports screen where the user is prompted to enter search values for Start Date, End Date, Description, Vendor, and Amount. The application filters on the search fields entered by the user.

![CustomResult](https://github.com/AlexisS05/accounting-ledger/assets/57822868/210219bd-441f-4887-9212-d2a7f7124add)

# Interesting piece of code.
This function right here. Mainly the boolean value. Setting it to true helped decide what to print because whatever is true should always print. If anything doesn't pass the condition then it basically ignores. The logical operators && helped alot even with the custom search.

![Piece of Code](https://github.com/AlexisS05/accounting-ledger/assets/57822868/c23f2ea1-b38f-4a48-bcd5-22b92102a30f)
