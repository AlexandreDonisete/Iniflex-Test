package application;

import entities.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        DecimalFormat df = new DecimalFormat(",##0.00");

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2799.93), "Gerente"));

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        System.out.println();
        System.out.println("Lista de funcionários: ");
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }

        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal(1.1)));
        }

        System.out.println();
        System.out.println("Lista com salários acrescidos de 10%: ");

        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println();
        System.out.print("Lista com funcionários agrupados pro função: ");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(System.out::println);
        });

        System.out.println();
        System.out.println("Funcionários que fazem aniversário em Outubro e Dezembro:");

        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12) {
                System.out.println(f);
            }
        }

        System.out.println();

        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + ", Idade: " + idade + " anos");
        System.out.println();

        System.out.println("Lista de funcionários em ordem alfabética: ");

        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.forEach(System.out::println);

        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Salários somados: " + df.format(totalSalarios));

        System.out.println();
        System.out.println("Quantidade de salários mínimos: ");

        for(Funcionario f : funcionarios) {
            BigDecimal qtdSalarioMinimo = f.getSalario().divide(new BigDecimal(1212.00), 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalarioMinimo + " salários mínimos.");
        }
    }
}