public class Roll {
    final Die d1 = new Die();
    final Die d2 = new Die();
    private final int sum;

    public Roll() {
        sum = d1.value + d2.value;
    }

    public int getSum() {
        return this.sum;
    }

    static class Die {

        // overly verbose but ascii art is fun
        private final String[] faces = new String[]{"""
                    ###########
                    #         #
                    #    *    #
                    #         #
                    ###########
                    """, """
                    ###########
                    # *       #
                    #         #
                    #       * #
                    ###########
                    """, """
                    ###########
                    #       * #
                    #    *    #
                    # *       #
                    ###########
                    """, """
                    ###########
                    # *     * #
                    #         #
                    # *     * #
                    ###########
                    """, """
                    ###########
                    # *     * #
                    #    *    #
                    # *     * #
                    ###########
                    """, """
                    ###########
                    # *     * #
                    # *     * #
                    # *     * #
                    ###########
                    """};
        private final int value = (int) (Math.random() * 6) + 1;

        public String getFace() {
            return this.faces[this.value - 1];
        }

        public int getValue() {
            return this.value;
        }
    }
}