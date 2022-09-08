package guru.qa;

import java.util.List;

public enum Sex { //енам нужен тогда, когда есть сущности, значение и количество которых заранее известно, напр, пол и дни недели
    MALE ("Мужчина"), FEMALE ("Женщина"); //создаем объекты с присваиванием значений путем вызова конструктора с this.desc

    public final String desc;

    Sex(String desc){
        this.desc = desc;
    }
}


