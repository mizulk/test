// version: 0.2;
import java.util.Scanner;

public class FunGame{

	private Scanner sc;
	private String[] name = new String[]{"六星","五星","四星","三星"};
	private int[] probabilities = new int[]{6,10,60,100};//2,8,50,40
	private int arrLength = name.length;
	private int[] history = new int[arrLength];
	private int[] tmpHistory = new int[arrLength];
	private int[] bestHistory = new int[arrLength];
	private int[] range = new int[]{6,5,4,3};
	private int bestScore;
	private int times;

	public FunGame(){
		sc = new Scanner(System.in);
		introduce();
	}

	private void systemMenuChoose(){
		int choose;
		int score = 0;
		int index = 0;
		mainLoop:while(true) {
			choose = getNum(-2, 5);
			switch(choose) {
				// 单抽
				case 1:
					System.out.println();
					System.out.println(name[start()]);
					System.out.println();
					break;
				// 十连
				case 2:
					for(int i = 0;i < 10;i++){
						index = start();
						tmpHistory[index]++;
						// 加权算法
						score += range[index];
					}
					showData(null,tmpHistory);
					// System.err.println(bestScore + ", " + score);
					if(score >= bestScore && tmpHistory[0] >= bestHistory[0]){
						bestScore = score;
						for(int i = 0;i<arrLength;i++){
							bestHistory[i] = tmpHistory[i];
						}
					}
					score = 0;
					tmpHistory = new int[arrLength];
					break;
				// 打开菜单
				case 3:
					systemMenu();
					break;
				// 统计
				case 4:
					System.out.println("已抽："+times);
					showData("统计结果如下：", history);
					break;
				// 查看最佳十连数据
				case 5:
					showData("本数据采用加权算法计算最佳数据\n最佳十连数据如下：", bestHistory);
					break;
				// 退出
				case -1:
					System.out.println("已退出！");
					break mainLoop;
				// 重置
				case -2:
					reset();
					break;
				default:
					System.err.println("no such a choose!");
			}
		}
	}

	public void showData(String info,int[] data){
		System.out.println();
		if(info!=null)
			System.out.println(info);
		for(int i = 0;i < arrLength;i++){
			System.out.println("\t" + name[i] + ": " + data[i]);
		}
		System.out.println();
	}

	public int start(){
		times++;
		int l = getRandom(1,100);
		if(times == 10 && (history[0] + history[1]  ==  0)){
			l = 8;
			System.out.println("！！！保底啦！！！");
		}
		int i = 0;
		if(l <= probabilities[0]){//2
			i = 0;
			history[0]++;
		}else if(l <= probabilities[1]){//8
			i =  1;
			history[1]++;
		}else if(l <= probabilities[2]){//50
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
		bestScore = 0;
		bestHistory = new int[arrLength];
		history = new int[arrLength];
		System.out.println("已重置");
	}

	private void systemMenu() {
		System.out.println("功能如下: 1.单抽 2.十连 3.打开菜单 4.统计 5.查看最佳十连数据 -2.重置 -1.退出\n");
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