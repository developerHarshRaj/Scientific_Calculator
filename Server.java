package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Listening on Port 5000");

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message from client: " + inputLine);
                    // Add calculation logic here
                    String result = calculate(inputLine);
                    out.println("Echo from server: " + result);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port 5000 or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
    // Inside the Server class

    public static String calculate(String input) {
        // Validate input
        if (input == null || input.trim().isEmpty()) {
            return "Invalid input";
        }
    
        String[] parts = input.split(" ");
        // if (parts.length != 3) {
        //     return "Invalid format. Expected format: operation operand1 operand2";
        // }
    
        String operation = parts[1];
        double num1, num2 = 0;
        try {
            if(parts.length == 2){
                operation = parts[0];
                num1 = Double.parseDouble(parts[1]);
            }
            else{ 
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[2]);
            }
        } catch (NumberFormatException e) {
            return "Invalid numbers: " + parts[0] + ", " + parts[2];
        }
    
        switch (operation) {
        case "+":
                return String.valueOf(num1 + num2);
        case "-":
                return String.valueOf(num1 - num2);
            // Add more cases for multiplication, division, etc.
        case "*":
            return String.valueOf(num1 * num2);
        case "%":
            if (num2 == 0) return "Error: Division by zero";
            return String.valueOf(num1 / num2);
        case "pow":
            return String.valueOf(Math.pow(num1, num2));
        case "sqrt":
            if (num1 < 0) return "Error: Square root of negative number";
            return String.valueOf(Math.sqrt(num1));
        case "log":
            if (num1 <= 0) return "Error: Log of non-positive number";
            return String.valueOf(Math.log(num1));
        case "log10":
            if (num1 <= 0) return "Error: Log10 of non-positive number";
            return String.valueOf(Math.log10(num1));
        case "sin":
            return String.valueOf(Math.sin(Math.toRadians(num1)));
        case "cos":
            return String.valueOf(Math.cos(Math.toRadians(num1)));
        case "tan":
            return String.valueOf(Math.tan(Math.toRadians(num1)));
        case "exp":
        return String.valueOf(Math.exp(num1));
        case "ln":
        if (num1 <= 0) return "Error: Ln of non-positive number";
        return String.valueOf(Math.log(num1));
        case "arcsin":
        if (num1 < -1 || num1 > 1) return "Error: Arcsin out of range";
        return String.valueOf(Math.toDegrees(Math.asin(num1)));
        case "arccos":
        if (num1 < -1 || num1 > 1) return "Error: Arccos out of range";
        return String.valueOf(Math.toDegrees(Math.acos(num1)));
        case "arctan":
        return String.valueOf(Math.toDegrees(Math.atan(num1)));
        case "sinh":
        return String.valueOf(Math.sinh(num1));
        case "cosh":
        return String.valueOf(Math.cosh(num1));
        case "tanh":
        return String.valueOf(Math.tanh(num1));
        case "percent":
        return String.valueOf(num1 / 100);
        case "abs":
        return String.valueOf(Math.abs(num1));
        default:
            return "Invalid operation";
        }
    }
    

}
