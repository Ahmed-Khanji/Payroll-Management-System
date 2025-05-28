# 📄 Payroll Management System – Java

This Java-based Payroll Management System automates salary calculations, tax deductions, and report generation for employee records. It reads employee data from a text file, validates each record, and processes valid entries while logging errors (e.g., minimum wage violations or malformed input).

The system uses object-oriented principles like **inheritance** and **abstraction** to model various tax deductions (EI, QPIP, QPP, Federal, and Provincial). Salaries are computed based on hours worked and hourly wage, and final reports are written to output files.

## Key Features

- 📂 File I/O for reading employee data and generating payroll reports  
- ⚠️ Custom exception handling for wage validation and data formatting  
- 📊 Dynamic array resizing for handling flexible input sizes  
- 🧱 Modular design using abstract classes for tax deduction logic
