import com.jakewharton.rxrelay.PublishRelay;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class Sample {

    public static void main(String[] args) {
//        just();
//        from();
//        error();
//        defer();
//        map();
//        flatMap();
//        publishSubject();
//        behaviorSubject();
//        replaySubject();
//        errorSubject();
        errorRelay();
    }

    /** Creation **/

    private static void just() {
        Observable.just("Hello")
                .subscribe(
                        new Action1<String>() {
                            public void call(String s) {
                                print(s + " " + "World");
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                println("!");
                            }
                        });
    }

    private static void from() {
        Observable.from(Arrays.asList("Hello", " ", "World"))
                .subscribe(s -> print(s), throwable -> {
                }, () -> println("!"));
    }

    private static void error() {
        Observable.error(new RuntimeException("Uh oh"))
                .subscribe(it -> println(it), throwable -> throwable.printStackTrace());
    }

    private static void defer() {
        Observable<String> just = Observable.just(printSomethingDumb());
        //        Observable<String> defer = Observable.defer(() -> Observable.just(printSomethingDumb()));
        //        Observable<String> fromCallable = Observable.fromCallable(Sample::printSomethingDumb);

        //        defer.subscribe();
        //        fromCallable.subscribe();
    }

    private static String printSomethingDumb() {
        String x = "Something dumb";
        println(x);
        return x;
    }

    /** Operators **/

    private static void map() {
        Observable.just("Hello, World!")
                .map(it -> it.substring(0, it.indexOf(",")))
                .subscribe(System.out::println);
    }

    private static void flatMap() {
        Observable.from(Arrays.asList("A", "B", "C"))
                .flatMap(t -> Observable.just(t + "!"))
                .subscribe(System.out::println);
    }

    /** Subjects **/

    private static void publishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(Sample::println); // Switch subscribe to after onNext

//    publishSubject.onNext("Hello, world!");

    }

    private static void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.onNext("Hello, World!");

        behaviorSubject.subscribe(Sample::println);
    }

    private static void replaySubject() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.onNext("One");
        replaySubject.onNext("Two");
        replaySubject.onNext("Three");

        replaySubject.subscribe(Sample::println);
    }

    private static void errorSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(Sample::println, Throwable::printStackTrace); // Switch subscribe to after onNext

        publishSubject.onNext("Hello, world!");

        publishSubject.onError(new RuntimeException("Oops"));

        publishSubject.onNext("Hello again, world!");
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void print(Object o) {
        System.out.print(o);
    }
}
