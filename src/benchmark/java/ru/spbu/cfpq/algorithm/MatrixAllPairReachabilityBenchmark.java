package ru.spbu.cfpq.algorithm;

import org.openjdk.jmh.annotations.*;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;

import java.util.concurrent.TimeUnit;

import static ru.spbu.cfpq.algorithm.CFReachabilityAlgorithms.matrixAllPairReachability;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class MatrixAllPairReachabilityBenchmark {

    ClassLoader classLoader = MatrixAllPairReachabilityBenchmark.class.getClassLoader();
    public final String benchmarkCase = "data/graphs/WorstCase/";

    @Param({"Brackets.txt"})
    public String grammarCase;

    @Param({"WorstCaseMatrix_3.txt", "WorstCaseMatrix_4.txt", "WorstCaseMatrix_5.txt", "WorstCaseMatrix_6.txt", "WorstCaseMatrix_7.txt"})
    public String graphCase;

    public ContextFreeGrammar grammar;
    public EdgeListGraph<Integer, Terminal> graph;

    @Setup(Level.Trial)
    public void setUp() {
        grammar = ContextFreeGrammar.readFromStream(classLoader.getResourceAsStream(benchmarkCase + "Grammars/" + grammarCase), true);
        graph = EdgeListGraph.readFromStream(classLoader.getResourceAsStream(benchmarkCase + "Matrices/" + graphCase));
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 20, timeUnit = TimeUnit.MILLISECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void testAllPairReachability() {
        matrixAllPairReachability(graph, grammar);
    }

}
