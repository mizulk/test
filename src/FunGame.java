
import java.util.Scanner;

public class FunGame{


	private Scanner sc;
	public String[] name = new String[]{"六星","五星","四星","三星"};
	private int arrLength = name.length;
	private int[] history = new int[arrLength];
	private int[] tmpHistory = new int[arrLength];
	private int times;

	public FunGame(){
		sc = new Scanner(System.in);
		introduce();
	}

	private void systemMenuChoose(){
		int choose;
		mainLoop:while(true) {
			choose = getNum(1, 6);
			switch(choose) {
				// 单抽
				case 1:
					System.out.println(name[start()]);
					System.out.println();
					break;
				// 十连
				case 2:
					for(int i = 0;i < 10;i++){
						tmpHistory[start()]++;
					}
					for(int i = 0;i < arrLength;i++){
						System.out.println("\t" + name[i] + ": " + tmpHistory[i]);
					}
					tmpHistory = new int[arrLength];
					System.out.println();
					break;
				// 打开菜单
				case 3:
					systemMenu();
					break;
				// 统计
				case 4:
					System.out.println("统计结果如下：");
					for(int i = 0;i < arrLength;i++){
						System.out.println("\t" + name[i] + ": " + history[i]);
					}
					System.out.println();
					break;
				// 重置
				case 5:
					reset();
					break;
				// 退出
				case 6:
					break mainLoop;
				default:
					System.err.println("no such a choose!");
			}
		}
	}

	public int start(){
		times++;
		int l = getRandom(1,100);
		if(times == 10 && (history[0] + history[1]  ==  0)){
			l = 8;
			System.out.println("！！！保底啦！！！");
		}
		int i = 0;
		if(l <= 2){//2
			i = 0;
			history[0]++;
		}else if(l <= 10){//8
			i =  1;
			history[1]++;
		}else if(l <= 60){//50
			i = 2;
			history[2]++;
		}else{//40
			i = 3;
			history[3]++;
		}
		return i;
	}

	private void reset(){
		times = 0;
		history = new int[arrLength];
	}

	private void systemMenu() {
		System.out.println("功能如下: 1.单抽 2.十连 3.打开菜单 4.统计 5.重置 6.退出\n");
	}

	private void introduce(){
		System.out.println("欢迎游玩此抽奖游戏，该游戏数据来源与明日方舟概率如下所示：");
		System.out.println("\t六星(0.02), 五星(0.08), 四星(0.5), 三星(0.4)");
		System.out.println("！！！没有保底算法，只有前十连必出五星的保底！！！\n");
		systemMenu();
		systemMenuChoose();
	}

	public int getNum(int min, int max){
		int num = 0;
		System.out.print("请输入你的选择：");
		while(true){
			try{
				num = Integer.parseInt(sc.next());
			}catch(NumberFormatException e){
				System.out.print("你输入的不是数字，请重新输入：");
				continue;
			}
			if(num < min || num > max) {
				System.out.print("你输入的不是数字不在"+ min +"到"+ max +"之间，请重新输入：");
				continue;
			}
			break;
		}
		return num;
	}

	public int getRandom(int min, int max){
		return (int)(Math.random() * (max - min + 1) + min);
	}

	public static void main(String[] agrs){
		new FunGame();
	}
}