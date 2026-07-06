pubic class Main{
    public static void main(Stirng[] args){
        //tao team
        Team me = new Team("me");

        //them cac vat pham vao gio
        me.addPlayer("An", "striker", 25);
        me.addPlayer("Binh", "midfielder", 27);
        me.addPlayer("Cuong", "striker", 26);

        //tinh gia tri trung binh
        //lay toan bo danh sach de tinh toan
        Set<Player> player = me.getAllItems();

        double totalAge = 0;
        int totalAgeCount = player.size();

        for (Player currentPlayer : player){
            totalAge = totalAge + currentPlayer.getAge();
        }

        double averageAge = totalAge / player.size();

        System.out.println("Team: " + me.getTeamName());
        System.out.println("Average age: " + averageAge);
    }
}