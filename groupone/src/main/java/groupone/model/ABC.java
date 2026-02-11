package groupone.model;

public abstract class ABC<T1,T2,T3> {

    protected T1 field1;
    protected T2 field2;
    protected T3 field3;

    private ABC() {
        throw new RuntimeException(this.getClass().getSimpleName()
                + ": экземпляр может быть создан только через билдер");
    }
    ABC(T1 field1, T2 field2, T3 field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public T1 getField1() {
        return field1;
    }
    public T2 getField2() {
        return field2;
    }
    public T3 getField3() {
        return field3;
    }

    /**
     * Билдер:
     * @param <T1> тип первого поля
     * @param <T2> тип второго поля
     * @param <T3> тип третьего поля
     * @param <TC> сам класс наследник
     */
    public abstract static class Builder<T1,T2,T3,TC extends ABC<T1,T2,T3>> {
        protected T1 field1;
        protected T2 field2;
        protected T3 field3;
        public Builder<T1,T2,T3,TC> setField1(T1 value) {
            field1 = value;
            return this;
        }
        public Builder<T1,T2,T3,TC> setField2(T2 value) {
            field2 = value;
            return this;
        }
        public Builder<T1,T2,T3,TC> setField3(T3 value) {
            field3 = value;
            return this;
        }
        public abstract TC build();
    }

}
