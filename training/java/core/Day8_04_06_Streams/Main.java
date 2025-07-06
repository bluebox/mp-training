package Day8_04_06_Streams;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    static int count=0;
    public static void main(String[] args) {
        int seed=1;
        var bStream = Stream.iterate(seed,i->i<=15,i->i+1).map(i->"B"+i);
        seed+=15;
        var iStream=Stream.iterate(seed,i->i+1).limit(15).map(i->"I"+i);
        seed+=15;
        int nSeed=seed;
        String[] labels=new String[15];
        Arrays.setAll(labels,i->"N"+(nSeed+i));
        var nStream=Arrays.stream(labels);
        seed+=15;
        var gStream=Stream.of("G46","G47","G48","G49","G50","G51","G52","G53","G54","G55","G56","G57","G58","G59","G60");
        seed+=15;
        int rSeed=seed;
        var oStream=Stream.generate(Main::getCount).limit(15).map(i->"O"+(rSeed+i));
        var streamBI = Stream.concat(bStream,iStream);
        var streamBIN = Stream.concat(streamBI, nStream);
        var streamBING = Stream.concat(streamBIN, gStream);
        Stream.concat(streamBING,oStream).forEach(System.out::println);
        Stream.generate(()->new Random().nextInt(rSeed,rSeed+15))
                .distinct()
                .limit(15)
                .map(i->"O"+i)
                .sorted()
                .forEach(System.out::println);
    }
    private static int getCount() {
        return count++;
    }
}
