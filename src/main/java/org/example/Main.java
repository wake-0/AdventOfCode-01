package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }


    final String path = "TODO";

    record Elf(List<Long> weights) {
        Long sum() {
            return weights.stream().reduce(0L, Long::sum);
        }
    }

    List<Elf> elves = new ArrayList<>();

    void run() throws IOException {
        // Read stacks
        readElves();
        printMaxElf();
    }

    void readElves() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path + "input.txt"))) {
            String line;

            Elf currentElf = new Elf(new ArrayList<>());
            while ((line = br.readLine()) != null) {

                if (line.equals("")) {
                    currentElf = new Elf(new ArrayList<>());
                    this.elves.add(currentElf);
                    continue;
                }

                currentElf.weights().add(Long.valueOf(line));
            }
        }
    }

    void printMaxElf() {
        Long maxWeight = this.elves.stream().max(Comparator.comparingLong(Elf::sum)).get().sum();
        System.out.println(maxWeight);
    }

}