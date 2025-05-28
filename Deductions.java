public abstract class Deductions {
    public abstract double calculateTax(double grossSalary);
}

class EI extends Deductions {
    public double calculateTax(double grossSalary) {
        double ei = (grossSalary * 1.64) / 100;
        return ei;
    }
}

class QPIP extends Deductions {
    public double calculateTax(double grossSalary) {
        double qpip = (grossSalary * 0.494) / 100;
        return Math.min(qpip, 484.12);
    }
}

class QPP extends Deductions {
    public double calculateTax(double grossSalary) {
        double qpp = (grossSalary * 10.8) / 100;
        return Math.min(qpp, 7700.40);
    }
}

class ProvincialTax extends Deductions {
    public double calculateTax(double grossSalary) {
        if (grossSalary <= 18571) return 0;
        else if (grossSalary <= 53255) return (grossSalary - 18571) * 0.14;
        else if (grossSalary <= 106495) return (grossSalary - 53255) * 0.19 + (53255 - 18571) * 0.14;
        else if (grossSalary <= 129590) return (grossSalary - 106495) * 0.24 + (106495 - 53255) * 0.19 + (53255 - 18571) * 0.14;
        else return (grossSalary - 129590) * 0.2575 + (129590 - 106495) * 0.24 + (106495 - 53255) * 0.19 + (53255 - 18571) * 0.14;
    }
}

class FederalTax extends Deductions {
    public double calculateTax(double grossSalary) {
        if (grossSalary < 16129) return 0;
        else if (grossSalary <= 57375) return (grossSalary - 16129) * 0.14;
        else if (grossSalary <= 114750) return (grossSalary - 57375) * 20.5 + (57375 - 16129) * 0.14;
        else if (grossSalary <= 177882) return (grossSalary - 114750) * 0.26 + (114750 - 57375) * 20.5 + (57375 - 16129) * 0.14;
        else if (grossSalary <= 253414) return (grossSalary - 177883) * 0.29 + (177883 - 114750) * 0.26 + (114750 - 57375) * 20.5 + (57375 - 16129) * 0.14;
        else return (grossSalary - 253414) * 0.33 + (253414 - 177883) * 0.29 + (177883 - 114750) * 0.26 + (114750 - 57375) * 20.5 + (57375 - 16129) * 0.14;
    }
}
