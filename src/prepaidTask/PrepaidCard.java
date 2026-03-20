package prepaidTask;

public class PrepaidCard {

    protected int balance;

    // Default Constructor
    public PrepaidCard() {
        balance = 100;
    }

    // Parameter Constructor
    public PrepaidCard(int balance) {
        this.balance = balance;
    }

    // Void method used because methods don't return anything
    public void topUp(int amount) {
        balance += amount;
    }

    public void pay(int amount){
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
        }
    }

    // Transfer chosen value from PrepaidCard to anotherCard
    public void transferTo(PrepaidCard anotherCard, int amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {

            // Removes money from the PrepaidCard
            balance -= amount;
            // Adds money to anotherCard
            anotherCard.balance += amount;
        }
    }

    // Check balance from outside the class
    public int getBalance() {
        return balance;
    }
}


