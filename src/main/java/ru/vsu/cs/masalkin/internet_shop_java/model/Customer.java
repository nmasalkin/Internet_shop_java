package ru.vsu.cs.masalkin.internet_shop_java.model;

/**
 * Класс представляет собой модель заказчика, содержащую информацию о его
 * идентификаторе, полном имени, адресе и номере телефона.
 */
public class Customer {

    /**
     * ID товара
     */
    private int id;

    /**
     * ФИО заказчика
     */
    private String fullname;

    /**
     * Адрес заказчика
     */
    private String address;

    /**
     * Номер телефона заказчика
     */
    private String phone_number;

    /**
     * Создает заказчика
     *
     * @param fullname  ФИО заказчика
     * @param address   адрес заказчика
     * @param phone_number номер телефона заказчика
     */
    public Customer(String fullname, String phone_number, String address) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.address = address;
    }

    /**
     * Получить ID заказчика
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Установить ID заказчика
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить ФИО заказчика
     *
     * @return ФИО
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Установить ФИО заказчика
     *
     * @param fullname ФИО
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Получить адрес заказчика
     *
     * @return адрес
     */
    public String getAddress() {
        return address;
    }

    /**
     * Установить адрес заказчика
     *
     * @param address адрес
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Получить номер телефона заказчика
     *
     * @return номер телефона
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * Установить номер телефона заказчика
     *
     * @param phone_number номер телефона
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Представление объекта заказа в виде строки.
     *
     * @return строковое представление заказчика
     */
    @Override
    public String toString() {
        return "ID заказчика = " + id + ", ФИО = " + fullname + ", адрес = " + address + ", номер телефона = " + phone_number;
    }
}
