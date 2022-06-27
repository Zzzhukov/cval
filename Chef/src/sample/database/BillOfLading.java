package sample.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillOfLading {
    private static int expenses;
    private static int countPerson = 1;
    public static StringBuilder menu = new StringBuilder();
    public static StringBuilder productsList;
    private static boolean firstDishesSet;
    private static boolean secondDishesSet;
    private static boolean thirdDishesSet;
    private static boolean fourthDishesSet;
    private static boolean fifthDishesSet;
    private static int countFirstDishesForOnePerson;
    private static int countSecondDishesForOnePerson;
    private static int countThirdDishesForOnePerson;
    private static int countFourthDishesForOnePerson;
    private static int countFifthDishesForOnePerson;
    public static final Map<String, Integer> productForFirstDish = new HashMap<>();
    public static final Map<String, Integer> productForSecondDish = new HashMap<>();
    public static final Map<String, Integer> productForThirdDish = new HashMap<>();
    public static final Map<String, Integer> productForFourthDish = new HashMap<>();
    public static final Map<String, Integer> productForFifthDish = new HashMap<>();
    public static Map<String, Integer> products = new HashMap<>();
    public static final ArrayList<String> productsArray = new ArrayList<>();

    public BillOfLading(){
        productsArray.add("Морковь");
        productsArray.add("Мясо");
        productsArray.add("Лук");
        productsArray.add("Свекла");
        productsArray.add("Капуста");
        productsArray.add("Рис");
        productsArray.add("Мука");
        productsArray.add("Картошка");
        productsArray.add("Яблоко");
        productsArray.add("Горох");
        productsArray.add("Огурец");
        productsArray.add("Помидор");
    }

    public static int getExpenses() {return expenses;}
    public static void setExpenses(int expenses) {BillOfLading.expenses = expenses;}
    public static int getCountPerson() {return countPerson;}
    public static boolean isFirstDishesSet() {return firstDishesSet;}
    public static boolean isSecondDishesSet() {return secondDishesSet;}
    public static boolean isThirdDishesSet() {return thirdDishesSet;}
    public static boolean isFourthDishesSet() {return fourthDishesSet;}
    public static boolean isFifthDishesSet() {return fifthDishesSet;}
    public static void setFirstDishesSet(boolean firstDishesSet) {BillOfLading.firstDishesSet = firstDishesSet;}
    public static void setSecondDishesSet(boolean secondDishesSet) {BillOfLading.secondDishesSet = secondDishesSet;}
    public static void setThirdDishesSet(boolean thirdDishesSet) {BillOfLading.thirdDishesSet = thirdDishesSet;}
    public static void setFourthDishesSet(boolean fourthDishesSet) {BillOfLading.fourthDishesSet = fourthDishesSet;}
    public static void setFifthDishesSet(boolean fifthDishesSet) {BillOfLading.fifthDishesSet = fifthDishesSet;}
    public static void incrementCountPerson(){countPerson++;}
    public static void decrementCountPerson(){countPerson--;}
    public static int getCountFirstDishesForOnePerson() {return countFirstDishesForOnePerson;}
    public static void setCountFirstDishesForOnePerson(int countFirstDishesForOnePerson) {BillOfLading.countFirstDishesForOnePerson = countFirstDishesForOnePerson;}
    public static int getCountSecondDishesForOnePerson() {return countSecondDishesForOnePerson;}
    public static void setCountSecondDishesForOnePerson(int countSecondDishesForOnePerson) {BillOfLading.countSecondDishesForOnePerson = countSecondDishesForOnePerson;}
    public static int getCountThirdDishesForOnePerson() {return countThirdDishesForOnePerson;}
    public static void setCountThirdDishesForOnePerson(int countThirdDishesForOnePerson) {BillOfLading.countThirdDishesForOnePerson = countThirdDishesForOnePerson;}
    public static int getCountFourthDishesForOnePerson() {return countFourthDishesForOnePerson;}
    public static void setCountFourthDishesForOnePerson(int countFourthDishesForOnePerson) {BillOfLading.countFourthDishesForOnePerson = countFourthDishesForOnePerson;}
    public static int getCountFifthDishesForOnePerson() {return countFifthDishesForOnePerson;}
    public static void setCountFifthDishesForOnePerson(int countFifthDishesForOnePerson) {BillOfLading.countFifthDishesForOnePerson = countFifthDishesForOnePerson;}
    public static void incrementCountFirstDishesForOnePerson(){countFirstDishesForOnePerson++;}
    public static void decrementCountFirstDishesForOnePerson(){countFirstDishesForOnePerson--;}
    public static void incrementCountSecondDishesForOnePerson(){countSecondDishesForOnePerson++;}
    public static void decrementCountSecondDishesForOnePerson(){countSecondDishesForOnePerson--;}
    public static void incrementCountThirdDishesForOnePerson(){countThirdDishesForOnePerson++;}
    public static void decrementCountThirdDishesForOnePerson(){countThirdDishesForOnePerson--;}
    public static void incrementCountFourthDishesForOnePerson(){countFourthDishesForOnePerson++;}
    public static void decrementCountFourthDishesForOnePerson(){countFourthDishesForOnePerson--;}
    public static void incrementCountFifthDishesForOnePerson(){countFifthDishesForOnePerson++;}
    public static void decrementCountFifthDishesForOnePerson(){countFifthDishesForOnePerson--;}
    public static void update(){
        products = new HashMap<>();
        expenses = 0;
        countPerson = 1;
        menu = new StringBuilder();
        firstDishesSet = false;
        secondDishesSet = false;
        thirdDishesSet = false;
        fourthDishesSet = false;
        fifthDishesSet = false;
        countFirstDishesForOnePerson = 0;
        countSecondDishesForOnePerson = 0;
        countThirdDishesForOnePerson = 0;
        countFourthDishesForOnePerson = 0;
        countFifthDishesForOnePerson = 0;
        
        for(String product : productsArray){
            productForFirstDish.put(product, 0);
            productForSecondDish.put(product, 0);
            productForThirdDish.put(product, 0);
            productForFourthDish.put(product, 0);
            productForFifthDish.put(product, 0);
            products.put(product, 0);
        }

        productForFirstDish.put("Морковь", 2);
        productForFirstDish.put("Мясо", 1);
        productForFirstDish.put("Лук", 2);
        productForFirstDish.put("Свекла", 2);
        productForFirstDish.put("Капуста", 1);

        productForSecondDish.put("Морковь", 1);
        productForSecondDish.put("Мясо", 2);
        productForSecondDish.put("Рис", 3);
        productForSecondDish.put("Лук", 1);

        productForThirdDish.put("Мука", 2);
        productForThirdDish.put("Мясо", 3);

        productForFourthDish.put("Свекла", 2);
        productForFourthDish.put("Картошка", 2);
        productForFourthDish.put("Морковь", 2);
        productForFourthDish.put("Яблоко", 1);
        productForFourthDish.put("Горох", 4);
        productForFourthDish.put("Огурец", 2);

        productForFifthDish.put("Картошка", 1);
        productForFifthDish.put("Мясо", 1);
        productForFifthDish.put("Морковь", 1);
        productForFifthDish.put("Огурец", 1);
        productForFifthDish.put("Мука", 1);
        productForFifthDish.put("Помидор", 1);
        productForFifthDish.put("Капуста", 1);
    }
}
