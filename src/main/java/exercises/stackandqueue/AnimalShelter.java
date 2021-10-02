package exercises.stackandqueue;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis. People must
 * adopt either the oldest (based on arrival time) of all animals at the shelter, or they can select whether they would
 * prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal
 * they would like. Create the data structures to maintain this system and implement operations such as enqueue,
 * dequeueAny, dequeueDog and dequeueCat. You may use the built-in LinkedList data structure.
 *
 * @author emanno
 * @version 1.0 Jul 24, 2017
 */
public class AnimalShelter {
    public static final long SLEEP_TIME = 100L;

    public static void main(String[] args) throws Exception {
        AnimalQueue shelter = new AnimalQueue();
        shelter.enqueue(new Dog("Rush"));
        Thread.sleep(SLEEP_TIME);
        shelter.enqueue(new Dog("Lex"));
        Thread.sleep(SLEEP_TIME);
        shelter.enqueue(new Cat("Lenny"));
        Thread.sleep(SLEEP_TIME);
        shelter.enqueue(new Cat("Felix"));
        Thread.sleep(SLEEP_TIME);
        shelter.enqueue(new Dog("Bernard"));

        System.out.println(shelter.dequeueCat().getName()); // expected Lenny
        System.out.println(shelter.dequeueDog().getName()); // expected Rush
        System.out.println(shelter.dequeueAny().getName()); // expected Lex
        System.out.println(shelter.dequeueDog().getName()); // expected Bernard
        System.out.println(shelter.dequeueAny().getName()); // expected Felix
    }

    public static class Animal {
        private final String name;
        private Date inTheShelterSince;


        public Animal(String name) {
            this.name = name;
        }


        public String getName() {
            return this.name;
        }


        public void setInTheShelterSince(Date since) {
            this.inTheShelterSince = since;
        }


        public boolean isInShelterLongerThan(Animal anotherAnimal) {
            return this.inTheShelterSince.compareTo(anotherAnimal.inTheShelterSince) < 0;
        }
    }

    public static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    public static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }

    public static class AnimalQueue {
        private final Queue<Dog> dogs = new LinkedList<>();
        private final Queue<Cat> cats = new LinkedList<>();


        public void enqueue(Animal animal) {
            animal.setInTheShelterSince(new Date());
            if (animal instanceof Dog)
                this.dogs.add((Dog) animal);
            else
                this.cats.add((Cat) animal);
        }


        public Dog dequeueDog() {
            return this.dogs.poll();
        }


        public Cat dequeueCat() {
            return this.cats.poll();
        }


        public Animal dequeueAny() {
            Dog oldestDog = this.dogs.peek();
            Cat oldestCat = this.cats.peek();

            if (oldestCat == null)
                return this.dequeueDog();
            if (oldestDog == null)
                return this.dequeueCat();

            if (oldestDog.isInShelterLongerThan(oldestCat)) {
                return this.dequeueDog();
            } else {
                return this.dequeueCat();
            }
        }

    }

}
