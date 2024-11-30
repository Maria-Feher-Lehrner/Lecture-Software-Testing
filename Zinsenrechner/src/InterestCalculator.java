import java.math.BigDecimal;
import java.text.DecimalFormat;

public class InterestCalculator {

    //***Method is used in Main to determine the interest rate based on the risk class input***
    double determineInterestRate(int risk) throws wrongInputException {
        double validatedInterestRate = 0;

        if (risk == 1 || risk == 2 || risk == 3) {
            validatedInterestRate = switch (risk) {
                case 1 -> 2.5;
                case 2 -> 7;
                case 3 -> 15;
                default -> 0;
            };
        } else {
            throw new wrongInputException("Invalid input: Please enter a number from 1 to 3.");
        }
        return validatedInterestRate;
    }


    //***Method is used to validate the desired max and min value of the initial capital.***
    //+++initialCapital+++
    //1) valid EC: Double with two digits after the decimal point, ranging from 100.00 to 10000000.00
    // --> Test Cases - concrete values: 99.99, 100.00, 100.01, 9999999.99, 10000000.00, 10000000.01
    // More than two digits after the decimal point can be entered too (but should be invalidated later)
    //2) invalid EC: everything outside of this range --> --> Test cases already covered in valid EC

    double validateInitialCapital (double initialCapital) throws wrongInputException {
        BigDecimal validatedCapital = BigDecimal.valueOf(initialCapital);
        if (validatedCapital.scale() > 2 || initialCapital < 100 || initialCapital > 10000000) {
            throw new wrongInputException("The capital to invest must be a value between 100,00 and 10.000.000,00 EURO and must not have more than 2 decimals.");
        } else {
            return initialCapital;
        }
    }


    //***Method is used in Main to validate the desired max and min range for duration.***
    int validateDuration(int duration) throws wrongInputException {
        if (duration < 1 || duration > 30) {
            throw new wrongInputException("The duration of investment has to be at least a year and can go up to 30 years. Investments are only possible for full years.");
        } else {
            return duration;
        }
    }


    //***Method is used in Main to calculate the final max-capital after interest gains.***
    double calculateEquityAfterInterest(double initialCapital, int duration, double validatedInterestRate) {
        double finalCapital = initialCapital * Math.pow((1 + validatedInterestRate / 100), duration);
        finalCapital = Math.round(finalCapital * 100.0) / 100.0;
        return finalCapital;
    }
}