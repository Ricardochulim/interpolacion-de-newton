import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterpolacionNewton {

    // Método para calcular las diferencias divididas
    public static double[][] calcularDiferenciasDivididas(List<Double> x, List<Double> y) {
        int n = y.size();
        double[][] dd = new double[n][n];

        // La primera columna son simplemente los valores de y
        for (int i = 0; i < n; i++) {
            dd[i][0] = y.get(i);
        }

        // Calcular las diferencias divididas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dd[i][j] = (dd[i + 1][j - 1] - dd[i][j - 1]) / (x.get(i + j) - x.get(i));
            }
        }

        return dd;
    }

    // Método para construir el polinomio interpolante simplificado
    public static String interpolacionNewton(List<Double> x, List<Double> y) {
        int n = y.size();
        StringBuilder polinomio = new StringBuilder();

        double[][] dd = calcularDiferenciasDivididas(x, y);

        // Construir el polinomio
        polinomio.append(y.get(0)); // El primer término
        for (int j = 1; j < n; j++) {
            double coeficiente = dd[0][j];

            if (coeficiente != 0) { // Solo agregar términos con coeficiente distinto de cero
                if (polinomio.length() > 0) {
                    polinomio.append(" + ");
                }

                if (j == 1) {
                    polinomio.append(coeficiente).append("x");
                } else {
                    polinomio.append(coeficiente).append("x^").append(j);
                }
            }
        }

        return polinomio.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese la cantidad de puntos
        System.out.print("Ingrese la cantidad de puntos: ");
        int n = scanner.nextInt();

        // Inicializar listas para almacenar los puntos
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();

        // Pedir al usuario que ingrese los puntos
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el valor de x" + (i+1) + ": ");
            double xi = scanner.nextDouble();
            System.out.print("Ingrese el valor de y" + (i+1) + ": ");
            double yi = scanner.nextDouble();
            x.add(xi);
            y.add(yi);
        }

        // Imprimir los puntos ingresados
        System.out.println("Puntos ingresados:");
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i+1) + ": " + x.get(i) + ", y" + (i+1) + ": " + y.get(i));
        }

        // Calcular el polinomio interpolante
        String polinomio = interpolacionNewton(x, y);

        // Imprimir la tabla de tabulación
        System.out.println("-----------------------");
        System.out.println("\npaso 1: Tabla de tabulación:");
        System.out.println("x\t|\tP(x)");
        System.out.println("-----------------------");
        for (int i = 0; i < n; i++) {
            System.out.println(x.get(i) + "\t|\t" + y.get(i));
        }

        // Imprimir las diferencias divididas
        System.out.println("-----------------------");
        System.out.println("\npaso 2: Diferencias divididas:");
        double[][] dd = calcularDiferenciasDivididas(x, y);
        System.out.println("-----------------------");
        for (int i = 0; i < dd.length; i++) {
            System.out.print("[" + y.get(i) + "]");
            for (int j = 1; j < dd[i].length; j++) {
                System.out.print("\t" + dd[i][j]);
            }
            System.out.println();
        }

        // Imprimir el polinomio interpolante
        System.out.println("-----------------------");
        System.out.println("\npaso 3: Polinomio interpolante: P(x) = " + polinomio);
        System.out.println("-----------------------");

        scanner.close();
    }
}
