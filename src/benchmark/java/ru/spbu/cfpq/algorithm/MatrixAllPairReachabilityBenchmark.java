package ru.spbu.cfpq.algorithm;

import org.openjdk.jmh.annotations.*;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static ru.spbu.cfpq.algorithm.CFReachabilityAlgorithms.matrixAllPairReachability;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class MatrixAllPairReachabilityBenchmark {

    ClassLoader classLoader = MatrixAllPairReachabilityBenchmark.class.getClassLoader();
    public final String benchmarkCase = "data/graphs/WorstCase/";

    @Param({"Brackets.txt"})
    public String grammarCase;

    @Param({"WorstCaseMatrix_3.txt", "WorstCaseMatrix_7.txt", "WorstCaseMatrix_10.txt", "WorstCaseMatrix_15.txt"})
    public String graphCase;

    public ContextFreeGrammar grammar;
    public EdgeListGraph<Integer, Terminal> graph;

    @Setup(Level.Trial)
    public void setUp() {
        grammar = ContextFreeGrammar.readFromFile(new File(classLoader.getResource(benchmarkCase + "Grammars/" + grammarCase).getFile()));
        graph = EdgeListGraph.readFromFile(new File(classLoader.getResource(benchmarkCase + "Matrices/" + graphCase).getFile()));
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 3, timeUnit = TimeUnit.MILLISECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void testAllPairReachability() {
        matrixAllPairReachability(graph, grammar);
    }

}
