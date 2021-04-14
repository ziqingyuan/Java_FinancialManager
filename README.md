# My Personal Project

## A personal financial manage app (account book)

- Can record purchases in (with type, date, name), can see the    
- anyone who want to track the money they spent
- Most of the financial app is very complex and not easy to use, I want to make a simple and easy use one. 

An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 
the bold and italic fonts correctly but they will appear correctly on GitHub.

### Users Stories
1. As a user, I want to be able to know the total money I spent so far
2. As a user, I want to be able to add a purchase to my account book
3. As a user, I want to be able to add a comment to my purchases
4. As a user, I want to be able to add my purchase's types
5. As a user, I want to be able to see my account book
6. As a user, I want to be able to delete a purchase
7. As a user, I want to be able to how many purchases
8. As a user, I want to be able to save my account book to file
9. As a user, I want to be able to load my account book to file

### Instructions for Grader
- You can generate the 1 required event by see the line below the title
- You can generate the 2, 3, 4 required event by click the "add new purchase" button
- You can generate the 5, 6, 7 required event by seeing the table (cancel when right click)
- You can trigger my audio component by add a success add purchase or enter a invalid amount
- You can save the state of my application by click the save button
- You can reload the state of my application by click the load button
 
### Phase 4: Task 2
- Robust class: AccountBook 
     - addPurchase(Double/int, String, String) throw invalidSpend exception when input number <= 0
     - delete(int) throw exception when input is not a valid index of the list