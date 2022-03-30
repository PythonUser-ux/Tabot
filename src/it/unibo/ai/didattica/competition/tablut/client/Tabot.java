package it.unibo.ai.didattica.competition.tablut.client;

// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.security.InvalidParameterException;
// import java.net.Socket;
// import java.util.Random;
// import javax.management.monitor.GaugeMonitor;
// import com.google.gson.Gson;

import java.util.Collections;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.net.UnknownHostException;

import it.unibo.ai.didattica.competition.tablut.domain.*;
import it.unibo.ai.didattica.competition.tablut.domain.State.Turn;

/**
 * 
 * @author A. Piretti, Andrea Galassi
 *
 */
public class Tabot extends TablutClient {

	private int game;

	public boolean in(List<int[]> list, int[] el){
		for (int[] element : list) {
			if(element[0]==el[0] && element[1]==el[1]){
				return true;
			}
		}
		return false;
	}
	/* It manipulates String coordinates and returns "selected_from" form coordinates in order to pass them to directly ai_movePawn
	The format of the output is [from_y,from_x,to_y,to_x] */
	public List<int[][]> all_moves(String state, List<int[]> camp_cells, int color){
		List<int[][]> moves = new ArrayList<int[][]>();
		int[] cou = new int[2];
		if(color==0){
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					int index=i+10*j;
					String piece=state.substring(index, index+1);
					if(piece.equals("W") || piece.equals("K")){
						for(int pos=i+1;pos<9;pos++){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=i-1;pos>-1;pos--){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=j+1;pos<9;pos++){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=j-1;pos>-1;pos--){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
					}}}}
		else{                                    // Commette l'errore di non considerare le mosse interne agli accampamenti
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					int index=i+10*j;
					if(state.substring(index, index+1).equals("B")){
						for(int pos=i+1;pos<9;pos++){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=i-1;pos>-1;pos--){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=j+1;pos<9;pos++){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
						for(int pos=j-1;pos>-1;pos--){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){int[][] move = new int[2][2];move[0][0]=j;move[0][1]=i;move[1][0]=cou[1];move[1][1]=cou[0];moves.add(move);}else{break;}}
					}}}}

		return moves;
	}
	public int mobility_w(String state, List<int[]> camp_cells){
		int mobilityw = 0;
		int[] cou = new int[2];
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					int index=i+10*j;
					String piece=state.substring(index, index+1);
					if(piece.equals("W") || piece.equals("K")){
						for(int pos=i+1;pos<9;pos++){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityw++;;}else{break;}}
						for(int pos=i-1;pos>-1;pos--){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityw++;;}else{break;}}
						for(int pos=j+1;pos<9;pos++){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityw++;;}else{break;}}
						for(int pos=j-1;pos>-1;pos--){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityw++;;}else{break;}}
					}}}
		return mobilityw;
	}
	public int mobility_b(String state, List<int[]> camp_cells){
		int mobilityb = 0;
		int[] cou = new int[2];
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					int index=i+10*j;
					String piece=state.substring(index, index+1);
					if(piece.equals("B")){
						for(int pos=i+1;pos<9;pos++){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityb++;;}else{break;}}
						for(int pos=i-1;pos>-1;pos--){cou[0]=pos; cou[1]=j;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityb++;;}else{break;}}
						for(int pos=j+1;pos<9;pos++){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityb++;;}else{break;}}
						for(int pos=j-1;pos>-1;pos--){cou[0]=i; cou[1]=pos;if(state.substring(cou[0]+cou[1]*10,cou[0]+cou[1]*10+1).equals("O") && !in(camp_cells, cou)){mobilityb++;;}else{break;}}
					}}}
		return mobilityb;
	}
	public String ai_movePawn(String state, int[] this_from, int[] this_to, int color, int capture_direction) { //0 è il bianco
		state=state.substring(0,90);
		int index_from = this_from[1]+10*this_from[0];                      //SWAP
		int index_to = this_to[1]+10*this_to[0];

		boolean king=state.substring(index_from,index_from+1).equals("K");

		String newState = state.substring(0, index_from)+"O"+state.substring(index_from+1, 90);
		if(color==1){newState = newState.substring(0, index_to)+"B"+newState.substring(index_to+1, 90);}
		else{if(king){newState = newState.substring(0, index_to)+"K"+newState.substring(index_to+1, 90);}else{
			newState = newState.substring(0, index_to)+"W"+newState.substring(index_to+1, 90);}}
			
		if (capture_direction!=0){
			switch(capture_direction){
				case 1:
					index_to++;
				break;
				case 2:
					index_to--;
				break;
				case 3:
					index_to+=10;
				break;
				case 4:
					index_to-=10;
				break;
				}
			newState = newState.substring(0, index_to)+"O"+newState.substring(index_to+1, 90);
			}
		return newState;
	}
	public int Capture_cell(String state, int[] destT, List<int[]> camp_cells, int color){        // non considera le catture del re
		int[] dest = destT.clone();
		int temp=dest[0];
		dest[0]=dest[1];
		dest[1]=temp;                                      // PERCHE' LE COORDINATE DELLE CASELLE DI DESTINAZIONE SONO SWAPPATE
		int[] cou1= new int[2];
		int[] cou2= new int[2];
		state=state.substring(0,90);
		String second_cell;
		int indexf = dest[0]+10*dest[1];
		if(dest[0]<7){                                  // Se c'è spazio per una cattura a destra
			cou2[0]=indexf%10+2;
			cou2[1]=indexf/10;
			cou1[0]=indexf%10+1;
			cou1[1]=indexf/10;
			second_cell=state.substring(indexf+2,indexf+3);
			if(color==0){if(state.substring(indexf+1,indexf+2).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){return 1;}}
			else{if(state.substring(indexf+1,indexf+2).equals("W") && (second_cell.equals("T") || second_cell.equals("B") || in(camp_cells,cou2))){return 1;}}
		}
		if(1<dest[0]){                                  // Se c'è spazio per una cattura a sinistra
			cou2[0]=indexf%10-2;
			cou2[1]=indexf/10;
			cou1[0]=indexf%10-1;
			cou1[1]=indexf/10;
			second_cell=state.substring(indexf-2,indexf-1);
			if(color==0){if(state.substring(indexf-1,indexf).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){return 2;}}
			else{if(state.substring(indexf-1,indexf).equals("W") && (second_cell.equals("T")|| second_cell.equals("B") || in(camp_cells,cou2))){return 2;}}
		}
		if(dest[1]<7){                                  // Se c'è spazio per una cattura in basso
			cou2[0]=indexf%10;
			cou2[1]=indexf/10+2;
			cou1[0]=indexf%10;
			cou1[1]=indexf/10+1;
			second_cell=state.substring(indexf+20,indexf+21);
			if(color==0){if(state.substring(indexf+10,indexf+11).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){return 3;}}
			else{if(state.substring(indexf+10,indexf+11).equals("W") && (second_cell.equals("T") || second_cell.equals("B") || in(camp_cells,cou2))){return 3;}}
		}
		if(1<dest[1]){                                  // Se c'è spazio per una cattura in alto
			cou2[0]=indexf%10;
			cou2[1]=indexf/10-2;
			cou1[0]=indexf%10;
			cou1[1]=indexf/10-1;
			second_cell=state.substring(indexf-20,indexf-19);
			if(color==0){if(state.substring(indexf-10,indexf-9).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){return 4;}}
			else{if(state.substring(indexf-10,indexf-9).equals("W") && (second_cell.equals("T") || second_cell.equals("B") || in(camp_cells,cou2))){return 4;}}
		}
		return 0;}
	public int final_heuristic(String state, List<int[]> camp_cells){
		// int mobility=0;
		int in_camps=0;
		//find the king
		int[] cou = new int[2];       // Coordinate casella del re
		int[] cou1 = new int[2];      // Coordinata dove guardo se un nero è nell'accampamento
		int surrounders=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				String piece=state.substring(i+j*10, i+j*10+1);
				if(piece.equals("B")){cou1[0]=i;cou1[1]=j;if(in(camp_cells, cou1)){in_camps++;}}
				else if(piece.equals("K")){cou[0]=i;cou[1]=j;}
			}
		}
		int king_index=cou[0]+cou[1]*10;
		if(state.substring(king_index+1, king_index+2).equals("B")){surrounders++;}
		if(state.substring(king_index-1, king_index).equals("B")){surrounders++;}
		if(state.substring(king_index+10, king_index+11).equals("B")){surrounders++;}
		if(state.substring(king_index-10, king_index-9).equals("B")){surrounders++;}

		// for(int pos=cou[0]+1;pos<9;pos++){if(!state.substring(pos+cou[1]*10, pos+cou[1]*10+1).equals("O") && !in(camp_cells,cou)){break;}else{mobility++;}}
		// for(int pos=cou[0]-1;pos>-1;pos--){if(!state.substring(pos+cou[1]*10, pos+cou[1]*10+1).equals("O") && !in(camp_cells,cou)){break;}else{mobility++;}}
		// for(int pos=cou[1]+1;pos<9;pos++){if(!state.substring(cou[0]+pos*10, cou[0]+pos*10+1).equals("O") && !in(camp_cells,cou)){break;}else{mobility++;}}
		// for(int pos=cou[1]-1;pos>-1;pos--){if(!state.substring(cou[0]+pos*10, cou[0]+pos*10+1).equals("O") && !in(camp_cells,cou)){break;}else{mobility++;}}

		return (java.lang.Math.abs(4-cou[0])+java.lang.Math.abs(4-cou[1])-surrounders*2+in_camps);   //+mobility);
		
	}
	public int WhiteCapture(String state, int[] destT, List<int[]> camp_cells){        // DA OTTIMIZZARE
		int[] dest = destT.clone();
		int temp=dest[0];
		dest[0]=dest[1];
		dest[1]=temp;                                      // PERCHE' LE COORDINATE DELLE CASELLE DI DESTINAZIONE SONO SWAPPATE
		int[] cou1= new int[2];
		int[] cou2= new int[2];
		state=state.substring(0,90);
		int score=0;
		String second_cell;
		int indexf = dest[0]+10*dest[1];
		if(dest[0]<7){                                  // Se c'è spazio per una cattura a destra
			cou2[0]=indexf%10+2;
			cou2[1]=indexf/10;
			cou1[0]=indexf%10+1;
			cou1[1]=indexf/10;
			second_cell=state.substring(indexf+2,indexf+3);
			if (state.substring(indexf+1,indexf+2).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){score++;}
		}
		if(1<dest[0]){                                  // Se c'è spazio per una cattura a sinistra
			cou2[0]=indexf%10-2;
			cou2[1]=indexf/10;
			cou1[0]=indexf%10-1;
			cou1[1]=indexf/10;
			second_cell=state.substring(indexf-2,indexf-1);
			if (state.substring(indexf-1,indexf).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){score++;}
		}
		if(dest[1]<7){                                  // Se c'è spazio per una cattura in basso
			cou2[0]=indexf%10;
			cou2[1]=indexf/10+2;
			cou1[0]=indexf%10;
			cou1[1]=indexf/10+1;
			second_cell=state.substring(indexf+20,indexf+21);
			if (state.substring(indexf+10,indexf+11).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){score++;}
		}
		if(1<dest[1]){                                  // Se c'è spazio per una cattura in alto
			cou2[0]=indexf%10;
			cou2[1]=indexf/10-2;
			cou1[0]=indexf%10;
			cou1[1]=indexf/10-1;
			second_cell=state.substring(indexf-20,indexf-19);
			if (state.substring(indexf-10,indexf-9).equals("B") && !in(camp_cells,cou1) && (second_cell.equals("T") || second_cell.equals("K") || second_cell.equals("W") || in(camp_cells,cou2))){score++;}
		}
		return score;}

	public int pieces_heuristic(String state){
		int white=0;
		int black=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				int index=i+10*j;
				String piece=state.substring(index, index+1);
				if(piece.equals("B")){black++;}
				else if(piece.equals("W")){white++;}
				}
			}
		return white*2-black;
		}
	
	public int is_goal(String state, List<int[]> camp_cells){
		int[] cou = new int[2];       // Coordinate casella del re     // WARNING Il re viene considerato catturato anche passivamente
		outerloop:
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(state.substring(i+j*10, i+j*10+1).equals("K")){cou[0]=i;cou[1]=j;break outerloop;}
			}
		}

		int[] x_checks = {1,2,6,7,0,8,0,8,0,8,0,8,1,2,6,7};
		int[] y_checks = {0,0,0,0,1,1,2,2,6,6,7,7,8,8,8,8};
		for(int i=0;i<16;i++){if(x_checks[i]==cou[0] && y_checks[i]==cou[1]){return 100;}}
		int x=cou[0];
		int y=cou[1];
		int king_index=x+10*y;

		String cell_up=state.substring(king_index-10, king_index-9);
		String cell_down=state.substring(king_index+10, king_index+11);
		String cell_left=state.substring(king_index-1, king_index);
		String cell_right=state.substring(king_index+1, king_index+2);

		boolean up=cell_up.equals("B"); int[] up_cou = {x,y-1};
		boolean down=cell_down.equals("B"); int[] down_cou = {x,y+1};
		boolean left=cell_left.equals("B"); int[] left_cou = {x-1,y};
		boolean right=cell_right.equals("B"); int[] right_cou = {x+1,y};

		boolean up_condition=(up || in(camp_cells, up_cou));
		boolean down_condition=(down || in(camp_cells, down_cou));
		boolean left_condition=(left || in(camp_cells, left_cou));
		boolean right_condition=(right || in(camp_cells, right_cou));

		if(king_index!=44){
		if(cell_up.equals("T") && down && left && right){return -100;}
		else if(cell_down.equals("T") && up && left && right){return -100;}
		else if(cell_left.equals("T") && up && down && right){return -100;}
		else if(cell_right.equals("T") && down && left && up){return -100;}
		else if(up_condition && down_condition && king_index!=34 && king_index!=54){return -100;}
		else if(left_condition && right_condition && king_index!=43 && king_index!=45){return -100;}
		}
		else{if(up && down && left && right){return -100;}}
		return 0;
	}

	public Tabot(String player, String name, int gameChosen, int timeout, String ipAddress) throws UnknownHostException, IOException {
		super(player, name, timeout, ipAddress);
		game = gameChosen;
	}
	
	public Tabot(String player, String name, int timeout, String ipAddress) throws UnknownHostException, IOException {
		this(player, name, 4, timeout, ipAddress);
	}
	
	public Tabot(String player, int timeout, String ipAddress) throws UnknownHostException, IOException {
		this(player, "Tabot", 4, timeout, ipAddress);
	}

	public Tabot(String player) throws UnknownHostException, IOException {
		this(player, "Tabot", 4, 60, "localhost");
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		int gametype = 4;
		String role = "";
		String name = "Tabot";
		String ipAddress = "localhost";
		int timeout = 60;

		if (args.length < 1) {
			System.out.println("You must specify which player you are (WHITE or BLACK)");
			System.exit(-1);
		} else {
			System.out.println(args[0]);
			role = (args[0]);
		}
		if (args.length == 2) {
			System.out.println(args[1]);
			timeout = Integer.parseInt(args[1]);
		}
		if (args.length == 3) {
			ipAddress = args[2];
		}
		System.out.println("Selected client: " + args[0]);

		Tabot client = new Tabot(role, name, gametype, timeout, ipAddress);
		client.run();
	}

	@Override
	public void run() {

		try {
			this.declareName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		State state;

		Game rules = null;
		switch (this.game) {
		case 1:
			state = new StateTablut();
			rules = new GameTablut();
			break;
		case 2:
			state = new StateTablut();
			rules = new GameModernTablut();
			break;
		case 3:
			state = new StateBrandub();
			rules = new GameTablut();
			break;
		case 4:
			state = new StateTablut();
			state.setTurn(State.Turn.WHITE);
			rules = new GameAshtonTablut(99, 0, "garbage", "fake", "fake");
			System.out.println("Ashton Tablut game");
			break;
		default:
			System.out.println("Error in game selection");
			System.exit(4);
		}
		List<int[]> pawns = new ArrayList<int[]>();
		List<int[]> empty = new ArrayList<int[]>();
		List<int[]> suitables_from = new ArrayList<int[]>();
		List<int[]> suitables_to = new ArrayList<int[]>();
		
		//creating camp cells and the throne
		int[] x={3,4,5,4,0,8,0,1,7,8,0,8,4,3,4,5,4};
		int[] y={0,0,0,1,3,3,4,4,4,4,5,5,7,8,8,8,4};
		List<int[]> camps = new ArrayList<int[]>();
		List<Integer> values_first = new ArrayList<Integer>();
		List<Integer> values_second = new ArrayList<Integer>();
		List<Integer> values_third = new ArrayList<Integer>();
		List<Integer> values_forth = new ArrayList<Integer>();
		int[] buf;
		for(int i = 0; i < 17; i++){
			buf = new int[2];
			buf[0]=x[i];
			buf[1]=y[i];
			camps.add(buf);}

		System.out.println("You are player " + this.getPlayer().toString() + "!");
		while (true) {
			try {
				this.read();
			} catch (ClassNotFoundException | IOException e1) {
				 
				e1.printStackTrace();
				System.exit(1);
			}
			// System.out.println("Current state:");
			state = this.getCurrentState();
			// System.out.println(state.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			// Controlla se sono il bianco
			if (this.getPlayer().equals(Turn.WHITE)){
				// List of my own pieces
				// Tocca a me
				if(this.getCurrentState().getTurn().equals(StateTablut.Turn.WHITE)) {
					// long mobility_w=mobility_w(state.toString(), camps);
					// long mobility_b=mobility_b(state.toString(), camps);
					// long mobility=mobility_w*mobility_w*mobility_w*mobility_b*mobility_b;
					// System.out.println("White Mobility "+mobility);
					long startTime = System.currentTimeMillis();
					int[] selected_from = null;
					int[] selected_to = null;
					boolean found = false;
					Action a = null;

					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if (state.getPawn(i, j).equalsPawn(State.Pawn.WHITE.toString())
									|| state.getPawn(i, j).equalsPawn(State.Pawn.KING.toString())) {
								buf = new int[2];
								buf[0] = i;
								buf[1] = j;
								pawns.add(buf);
							}
						}
					}
					// Looking for moves
					int from_pos=0;
					int same_from_pos=-1;
					int to_pos=0;
					while (!found){
						selected_from = pawns.get(from_pos);
						String from = state.getBox(selected_from[0],selected_from[1]);
						if (same_from_pos!=from_pos){      // Avoids computing destination cells more than once
							
							for(int i=0;i<9;i++){
								buf = new int[2];
								buf[0] = i;
								buf[1] = selected_from[1];
								if(i!=selected_from[0]){
									empty.add(buf);
									}
								}

							for(int i=0;i<9;i++){
								buf = new int[2];
								buf[0] = selected_from[0];
								buf[1] = i;
								if(i!=selected_from[1]){
									empty.add(buf);
									}
								}

							same_from_pos++;
							}
						selected_to = empty.get(to_pos);
						String to = state.getBox(selected_to[0],selected_to[1]);

						try {
							a = new Action(from, to, State.Turn.WHITE);
							rules.checkMove(state, a);
							suitables_from.add(selected_from);
							suitables_to.add(selected_to);

							// WHITE REASONING
							// DEEP=1
							int cap1 = Capture_cell(state.toString(), selected_to, camps, 0);
							String state1 = ai_movePawn(state.toString(), selected_from, selected_to, 0, cap1);
							int is_goal=is_goal(state1,camps);
							if(is_goal!=0){values_first.add(is_goal);found=true;}
							int final_heuristic=final_heuristic(state1,camps);
							// DEEP=2
							for(int[][]move1:all_moves(state1,camps,1)){
								int cap2 = Capture_cell(state1, move1[1], camps, 1);
								String state2 = ai_movePawn(state1, move1[0], move1[1], 1, cap2);
								is_goal=is_goal(state2,camps);
								if(is_goal!=0){values_second.add(is_goal);continue;}
								// DEEP=3
								for(int[][]move2:all_moves(state2,camps,0)){
									int cap3 = Capture_cell(state2, move2[1], camps, 0);
									String state3 = ai_movePawn(state2, move2[0], move2[1], 0, cap3);
									is_goal=is_goal(state3,camps);
									if(is_goal!=0){values_third.add(is_goal);continue;}
									// DEEP=4
									for(int[][]move3:all_moves(state3,camps,1)){
										int cap4 = Capture_cell(state3, move3[1], camps, 1);
										String state4 = ai_movePawn(state3, move3[0], move3[1], 1, cap4);
										is_goal=is_goal(state4,camps);
										if(is_goal!=0){values_forth.add(is_goal);continue;}
										int pieces_heuristic=0;
										if(cap1!=0){pieces_heuristic+=8;}
										if(cap2!=0){pieces_heuristic-=16;}
										if(cap3!=0){pieces_heuristic+=8;}
										if(cap4!=0){pieces_heuristic-=16;}
										values_forth.add(pieces_heuristic+final_heuristic);
										}
										values_third.add(Collections.min(values_forth));
										values_forth.clear();
									}
									values_second.add(Collections.max(values_third));
									values_third.clear();
								}
								values_first.add(Collections.min(values_second));
								values_second.clear();

						} catch (Exception e){}//e.printStackTrace();

						if(to_pos==15){from_pos++;to_pos=0;empty.clear();}      // Aggiorna gli indici di posizione del pezzo e della casella
							else{to_pos++;}

						// Le mosse di ogni mio pezzo sono state considerate
						if(from_pos==pawns.size()){
							found = true;}
					}
						      	                           // CHOOSING THE BEST MOVE
					try {
						int chosen_move=0;
						for(int i=1;i<suitables_from.size();i++){if(values_first.get(i)>values_first.get(chosen_move)){chosen_move=i;}}
						selected_from=suitables_from.get(chosen_move);
						selected_to=suitables_to.get(chosen_move);
						String from = state.getBox(selected_from[0],selected_from[1]);
						String to = state.getBox(selected_to[0],selected_to[1]);
						a = new Action(from, to, State.Turn.WHITE);
						// System.out.println("Mossa scelta: " + a.toString());
						rules.Move(state, a);
						this.write(a);
					} catch (Exception e) {} //e.printStackTrace();
					//for(int i=0;i<suitables_from.size();i++){System.out.print(values_first.get(i));System.out.print("*");}System.out.println();
					empty.clear();
					pawns.clear();
					suitables_from.clear();
					suitables_to.clear();
					values_first.clear();
				
					long endTime = System.currentTimeMillis();
					System.out.println("That took " + (endTime - startTime)/100 + " deciseconds");
				}
				// Turno dell'avversario
				else if (state.getTurn().equals(StateTablut.Turn.BLACK)) {
					System.out.println("Waiting for your opponent move... ");
				}
				// ho vinto
				else if (state.getTurn().equals(StateTablut.Turn.WHITEWIN)) {
					System.out.println("YOU WIN!");
					System.exit(0);
				}
				// ho perso
				else if (state.getTurn().equals(StateTablut.Turn.BLACKWIN)) {
					System.out.println("YOU LOSE!");
					System.exit(0);
				}
				// pareggio
				else if (state.getTurn().equals(StateTablut.Turn.DRAW)) {
					System.out.println("DRAW!");
					System.exit(0);
				}
			// Controlla se sono il nero
			} else {

				if (this.getCurrentState().getTurn().equals(StateTablut.Turn.BLACK)) {
					// long mobility_w=mobility_w(state.toString(), camps);
					// long mobility_b=mobility_b(state.toString(), camps);
					// long mobility=mobility_w*mobility_w*mobility_b*mobility_b*mobility_b;
					// System.out.println("Black Mobility "+mobility_b);
					long startTime = System.currentTimeMillis();
					int[] selected_from = null;
					int[] selected_to = null;
					boolean found = false;
					Action a = null;
					
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if (state.getPawn(i, j).equalsPawn(State.Pawn.BLACK.toString())) {
								buf = new int[2];
								buf[0] = i;
								buf[1] = j;
								pawns.add(buf);
							}
						}
					}

					// Looking for moves
					int from_pos=0;
					int same_from_pos=-1;
					int to_pos=0;
					while (!found){
						selected_from = pawns.get(from_pos);
						String from = state.getBox(selected_from[0],selected_from[1]);
						if (same_from_pos!=from_pos){      // Se non cambia la perdina le caselle di destinazione non vengono calcolate di nuovo
							
							for(int i=0;i<9;i++){
								buf = new int[2];
								buf[0] = i;
								buf[1] = selected_from[1];
								if(i!=selected_from[0]){
									empty.add(buf);
									}
								}

							for(int i=0;i<9;i++){
								buf = new int[2];
								buf[0] = selected_from[0];
								buf[1] = i;
								if(i!=selected_from[1]){
									empty.add(buf);
									}
								}

							same_from_pos++;     // Alla prossima mossa se la pedina è la stessa non vengono ricalcolate le caselle della stessa riga e della stessa colonna
							}
						selected_to = empty.get(to_pos);
						String to = state.getBox(selected_to[0],selected_to[1]);

						try {
							a = new Action(from, to, State.Turn.BLACK);
							rules.checkMove(state, a);
							suitables_from.add(selected_from);
							suitables_to.add(selected_to);

							// BLACK REASONING
							// DEEP=1
							int cap1=Capture_cell(state.toString(), selected_to, camps, 1);
							String state1 = ai_movePawn(state.toString(), selected_from, selected_to, 1, cap1);
							int is_goal=is_goal(state1,camps);
							int final_heuristic=final_heuristic(state1,camps);
							if(is_goal!=0){values_first.add(is_goal);found=true;}
							else{
							// DEEP=2
							for(int[][]move1:all_moves(state1,camps,0)){
								int cap2=Capture_cell(state1, move1[1], camps, 0);
								String state2 = ai_movePawn(state1, move1[0], move1[1], 0, cap2);
								is_goal=is_goal(state2,camps);
								if(is_goal!=0){values_second.add(is_goal);continue;}
								// DEEP=3
								for(int[][]move2:all_moves(state2,camps,1)){
									int cap3=Capture_cell(state2, move2[1], camps, 1);
									String state3 = ai_movePawn(state2, move2[0], move2[1], 1, cap3);
									is_goal=is_goal(state3,camps);
									if(is_goal!=0){values_third.add(is_goal);continue;}
									// DEEP=4
									for(int[][]move3:all_moves(state3,camps,0)){
										int cap4=Capture_cell(state3, move3[1], camps, 0);
										String state4 = ai_movePawn(state3, move3[0], move3[1], 0, cap4);
										is_goal=is_goal(state4,camps);
										if(is_goal!=0){values_forth.add(is_goal);continue;} // IN QUESTO CASO NON CALCOLO L'HEURISTIC
										int pieces_heuristic=0;
										if(cap1!=0){pieces_heuristic-=16;}
										if(cap2!=0){pieces_heuristic+=8;}
										if(cap3!=0){pieces_heuristic-=16;}
										if(cap4!=0){pieces_heuristic+=8;}
										values_forth.add(pieces_heuristic+final_heuristic);
										}
										values_third.add(Collections.max(values_forth));
										values_forth.clear();
									}
									values_second.add(Collections.min(values_third));
									values_third.clear();
								}
								values_first.add(Collections.max(values_second));
								values_second.clear();
								}
						} catch (Exception e){}//e.printStackTrace();

						if(to_pos==15){from_pos++;to_pos=0;empty.clear();}      // Aggiorna gli indici di posizione del pezzo e della casella
							else{to_pos++;}

						// Le mosse di ogni mio pezzo sono state considerate
						if(from_pos==pawns.size()){
							found = true;}
					}
						      	                           // CHOOSING THE BEST MOVE
					try {
						int chosen_move=0;
						for(int i=1;i<suitables_from.size();i++){if(values_first.get(i)<values_first.get(chosen_move)){chosen_move=i;}}
						selected_from=suitables_from.get(chosen_move);
						selected_to=suitables_to.get(chosen_move);
						String from = state.getBox(selected_from[0],selected_from[1]);
						String to = state.getBox(selected_to[0],selected_to[1]);
						a = new Action(from, to, State.Turn.BLACK);
						// System.out.println("Mossa scelta: " + a.toString());
						//System.out.println(values_first.get(chosen_move));
						rules.Move(state, a);
						this.write(a);
					} catch (Exception e) {}
					//for(int i=0;i<suitables_from.size();i++){System.out.print(values_first.get(i));System.out.print("*");}System.out.println();
					empty.clear();
					pawns.clear();
					suitables_from.clear();
					suitables_to.clear();
					values_first.clear();

					long endTime = System.currentTimeMillis();
					System.out.println("That took " + (endTime - startTime)/100 + " deciseconds");
				}
				// Turno dell'avversario
				else if (state.getTurn().equals(StateTablut.Turn.WHITE)) {
					System.out.println("Waiting for your opponent move... ");
				} else if (state.getTurn().equals(StateTablut.Turn.WHITEWIN)) {
					System.out.println("YOU LOSE!");
					System.exit(0);
				} else if (state.getTurn().equals(StateTablut.Turn.BLACKWIN)) {
					System.out.println("YOU WIN!");
					System.exit(0);
				} else if (state.getTurn().equals(StateTablut.Turn.DRAW)) {
					System.out.println("DRAW!");
					System.exit(0);
				}
			}
		}
	}
}