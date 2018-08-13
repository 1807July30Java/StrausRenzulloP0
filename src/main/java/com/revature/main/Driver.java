package com.revature.main;

import com.revature.dao.*;
import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;

import java.util.Scanner;

class Driver {
    private User activeUser;
    private UserDAO ud = new UserDAOImpl();
    private AccountDAO ad = new AccountDAOImpl();
    private TransactionDAO tx = new TransactionDAOImpl();

    private void getUser() {
        while (this.activeUser == null) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Username:");
                String username = scan.next();
                System.out.print("Password:");
                String password = scan.next();
                this.activeUser = ud.getUser(username, password);
                //            activeUser = new User(0, username, password, true);
                System.out.println(activeUser.getUsername());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void userAction(String op, String[] args) {
        try {
            switch (op) {
                case "w":
                    int account1 = Integer.parseInt(args[2]);
                    int value1 = Integer.parseInt(args[1]);
                    Account curr = ad.getAccountById(account1);
                    System.out.println("withdraw " + value1 + " from account " + account1);
                    if (curr.getOwnerID() == activeUser.getUserID()) {
                        ad.withdraw(curr, activeUser.getUserID(), value1);
                    } else {
                        System.out.println("you do not own this account");
                    }
                    break;
                case "d":
                    int account2 = Integer.parseInt(args[2]);
                    int value2 = Integer.parseInt(args[1]);
                    Account curr2 = ad.getAccountById(account2);
                    System.out.println("deposit " + value2 + " to account " + account2);
                    if (curr2.getOwnerID() == activeUser.getUserID()) {
                        ad.deposit(curr2, activeUser.getUserID(), value2);
                    } else {
                        System.out.println("you do not own this account");
                    }
                    break;
                case "add":
                    Account a = new Account(0, activeUser.getUserID());
                    System.out.println("added account to " + activeUser.getUsername());
                    ad.addAccount(a);
                    break;
                case "del":
                    int accountid = Integer.parseInt(args[1]);
                    System.out.println("delete empty account " + accountid);
                    Account current = ad.getAccountById(accountid);
                    ad.deleteAccount(current);
                    break;
                case "acc":
//                  System.out.println(ad.getAllAccounts(activeUser));
                    System.out.println();
                    for (Account acct : ad.getAllAccounts(activeUser)) {
                        System.out.println(acct);
                    }
                    break;
                case "TxA":
                    if (args.length > 1) {
                        int accid = Integer.parseInt(args[1]);
                        Account test = ad.getAccountById(accid);
                        if (test.getAccountID() == activeUser.getUserID() || activeUser.isAdmin()) {
                            System.out.println();
                            for(Transaction t : tx.getTransactionsForUser(accid)) {
                                System.out.println(t);
                            }
                        } else {
                            System.out.println("This account does not belong to you");
                        }
                    } else {
                        System.out.println("Please Specify an account");
                    }
                    break;
                case "TxU":
                    if (!activeUser.isAdmin()) {
                        System.out.println();
                        for(Transaction t : tx.getTransactionsForUser(activeUser.getUserID())) {
                            System.out.println(t);
                        }
                    } else {
                        if (args.length > 1) {
                            int uid = Integer.parseInt(args[1]);
                            System.out.println();
                            for(Transaction t : tx.getTransactionsForUser(uid)) {
                                System.out.println(t);
                            }
                        } else {
                            System.out.println();
                            for(Transaction t : tx.getTransactionsForUser(activeUser.getUserID())) {
                                System.out.println(t);
                            }
                        }
                    }
                    break;
                case "new":
                    if (activeUser.isAdmin()) {
                        String username = args[1];
                        System.out.println(args[1]);
                        String pass = args[2];
                        System.out.println(args[2]);
                        boolean isAd = Boolean.valueOf(args[3]);
                        System.out.println(args[3]);
                        System.out.println("added user " + username + "with pass " + pass + "is admin =" + isAd);
                        ud.newUser(username, pass, isAd);
                    }
                    break;
                case "help":
                    System.out.println("w [amt][accountid] : withdraw amt from account with accountid");
                    System.out.println("d [amt][accountid] : deposit amt to account with accountid");
                    System.out.println("add : create new empty account");
                    System.out.println("del [accountid] : delete empty account with accountid");
                    System.out.println("acc : retrieves all accounts the user has");
                    System.out.println("TxA [accountid] : shows all transactions from account with accountid, only works if account belongs to you (or user is admin)");
                    System.out.println("TxU : shows all of your transactions");
                    System.out.println("TxU [userid] : if current user is admin, they can see all transactions for a given user with userid");
                    System.out.println("new [username][password][isadmin] : Creates new user with username, password, and isadmin");
                    System.out.println("quit : quits application");
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Command not recognized");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid user input, use 'help' to see valid commands.");
        }
    }

    public static void main(String[] args) {
        Driver D = new Driver();
        D.getUser();
        Scanner scan = new Scanner(System.in);
        String op = null;
        while (op == null || !op.equals("quit")) {
            System.out.print("Enter command then arguments. Type 'help' for list of commands:");
//            int index = 0;
            String op_raw = scan.nextLine();
            String[] A = op_raw.split("[^a-zA-Z0-9]+");
            op = A[0];
            D.userAction(op, A);
        }
    }
}
