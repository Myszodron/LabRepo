package prepaidTask;

public class PrepaidCardTester {

    public static void main(String[] args) {

        // Card with default and custom balance
        PrepaidCard c1 = new PrepaidCard();
        PrepaidCard c2 = new PrepaidCard(43);

        // Check balance before doing any operations
        System.out.println("Starting balance: ");
        System.out.println("Prepaid Card 1: " + c1.getBalance());
        System.out.println("Prepaid Card 2: " + c2.getBalance());

        // Top up PrepaidCard 2
        c2.topUp(120);
        // Pay with PrepaidCard 1
        c1.pay(10);

        System.out.println("\nBalance after operations: ");
        System.out.println("PrepaidCard 1 after paying: " + c1.getBalance());
        System.out.println("PrepaidCard 2 after top up: " + c2.getBalance());

        // Transfer given amount from PrepaidCard1 to PrepaidCard2
        c1.transferTo(c2,35);

        System.out.println("\nBalance after transfers: ");
        System.out.println("PrepaidCard 1 after transfer: " + c1.getBalance());
        System.out.println("PrepaidCard 2 after getting a transfer: " + c2.getBalance());
    }
}

