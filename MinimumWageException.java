public class MinimumWageException extends Exception {
    public MinimumWageException() {
        super("Error: Hourly wage is below the legal minimum of $15.75.");
    }
}
