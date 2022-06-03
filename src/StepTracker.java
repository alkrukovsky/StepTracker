import java.util.*;

public class StepTracker {

    private HashMap<String, int[]> dataBase;
    private static int target = 10000;
    private String month;
    private List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December");

    public void getStatistic() {
        do {
            String[] userInput = inputData();
            month = userInput[0];
        } while (checkData());
        int[] days = dataBase.get(month);
        String monthStatic = "";
        int amountSteps = 0;
        int count = 0;
        int maxSet = 0;
        for (int i = 0; i<30; i++) {
            monthStatic += i+1 + " day: " + days[i] + ", ";
            amountSteps += days[i];
            if (days[i] > StepTracker.target) {
                count++;
                if (count > maxSet) {
                    maxSet = count;
                }
            } else {
                count = 0;
            }
        }
        int[] days1 = days;
        Arrays.sort(days1);
        System.out.println(monthStatic);
        System.out.println("Obwee kolichestvi shagov za mesyac: " + amountSteps);
        System.out.println("Maximalnoe kolichestvo shagov: " + days1[29]);
        System.out.println("Srednee kolichestvo shagov: " + amountSteps / 30);
        System.out.println("Proidennoe rasstoyanie: " + amountSteps * 0.75 / 1000 + " km");
        System.out.println("Sozheno kilokallorii: " + amountSteps * 50 / 1000);
        System.out.println("Luchshaya seriya: " + maxSet);
    }

    public void setTarget() {
        int newTarget;
        do {
            String[] userInput = inputData();
            newTarget = Integer.valueOf(userInput[0]);
        } while (checkData(newTarget));
        StepTracker.target = newTarget;
    }

    private boolean checkData(Integer steps) {
        boolean error = false;
        if (steps < 0) {
            System.out.println("Shagi ne mogut byt menshe 0.");
            error = true;
        }
        return error;
    }

    private boolean checkData() {
        boolean error = false;
        if (!(months.contains(month))) {
            System.out.println("Mesyac vveden neverno.");
            error = true;
        }
        return error;
    }

    private boolean checkData(Integer day, Integer steps) {
        boolean error = false;
        if (!(months.contains(month))) {
            System.out.println("Mesyac vveden neverno.");
            error = true;
        }
        if (!((day >= 1) && (day <= 30))) {
            System.out.println("Den vvevden neverno.");
            error = true;
        }
        if (steps < 0) {
            System.out.println("Shagi ne mogut byt menshe 0.");
            error = true;
        }
        return error;
    }

    private String[] inputData() {
        System.out.println("Vvedite dannye");
        Scanner scanner = new Scanner(System.in);
        String[] userInput = scanner.nextLine().split(" ");
        return userInput;
    }

    private void putData() {
        int day;
        int steps;
        do {
            String[] userInput = inputData();
            month = userInput[0];
            day = Integer.valueOf(userInput[1]);
            steps = Integer.valueOf(userInput[2]);
        } while (checkData(day, steps));

        if (!(dataBase.containsKey(month))) {
            int[] days = new int[30];
            dataBase.put(month, days);
        }

        int[] days = dataBase.get(month);
        days[day-1] = steps;
        dataBase.put(month, days);
    }

    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();

        stepTracker.dataBase = new HashMap<String, int[]>();

        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 0) {
            if (userInput == 1) {
                stepTracker.putData();
            } else if (userInput == 2) {
                stepTracker.getStatistic();
            } else if (userInput == 3) {
                stepTracker.setTarget();
            } else {
                System.out.println("Nevernyi vvod");
            }

            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Programma zavershena");
    }

    private static void printMenu() {
        System.out.println("1 - Vnesti dannye");
        System.out.println("2 - Vyvesti statistiku");
        System.out.println("3 - Zadat cel");
        System.out.println("0 - Zavershit rabotu");
    }
}
