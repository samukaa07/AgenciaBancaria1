package Programa;

import java.util.ArrayList;
import java.util.Scanner;

public class AgenciaBancaria {

	// vamos pegar os dados que o cliente usu�rio esta digitando
	static Scanner input = new Scanner(System.in);
	
	// nossa agencia bancaria tem varias contas precisamos de uma lista de contas
	static ArrayList<Conta> contasBancarias;
	
	
	public static void main(String[] args) {
		
		// instanciando contasBancarias
		contasBancarias = new ArrayList<Conta>();	// arrayList do tipo <Conta>

		//criando um menu de opera��es sacar ou transferir ou depositar
		operacoes();
		
	}


	public static void operacoes() {

		System.out.println("------------------------------------------------------");
		System.out.println("-------------Bem vindos a nossa Ag�ncia---------------");
		System.out.println("------------------------------------------------------");
		System.out.println("***** Selecione uma Opera��o que deseja realizar *****");
		System.out.println("|		Op��o 1 - Criar conta		|");
		System.out.println("|		Op��o 2 - Depositar			|");
		System.out.println("|		Op��o 3 - Sacar				|");
		System.out.println("|		Op��o 4 - Transferir		|");
		System.out.println("|		Op��o 5 - Listar			|");
		System.out.println("|		Op��o 6 - Sair				|");
		
		// vamos criar um swtich case para chamar cada m�todo de acordo com cada op��o que o usu�rio digitar
		// chamar imput que chamou acima e pegar o inteiro
		int operacao = input.nextInt();
		
		//agora fazendo estrutura do swhitch case.
		
		switch(operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			transferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			System.out.println(" Obrigado por usar nossa ag�ncia");
			System.exit(0); // system.exit sai do programa
			
		default:  	// caso usu�rio digitar alguma op��o que n�o tenha 
			System.out.println("op��o inv�lida!");
			operacoes(); //aqui chama menu principal operacoes pq n quer sair por ter digitado numero errado quero que chama menu denovo
			break;
					
			
		}
		
	}
	
	// criando o m�todo criar conta que fizemos no switch
	// aqui precisamos de nome, cpf, e-mail e passar essa pessoa em uma conta e a conta e uma lista de conta
	public static void criarConta() {
		
		System.out.println("\nNome:");
		// a gente define uma variavel que vai armazenar esse nome que o usuario digitar e receber o que usuario digitar 
		String nome = input.next();

		System.out.println("\nCPF:");
		String cpf = input.next();
		
		System.out.println("\nEmail:");
		String email = input.next();
		
		// agora precisamos instanciar uma pessoa e passar esses dados para ela 
		Pessoa pessoa = new Pessoa(nome, cpf, email);
		
		// aqui a gente quer adicionar essa pessoa a essa conta
		Conta conta = new Conta(pessoa);
		
		//preciso adicionar essa conta a cima na minha lista de contas
		//aqui chamo meu metodo contasBancarias.add e ele ja passa minha conta aqui 
		contasBancarias.add(conta);
		
		//agora a gente s� fala para o cliente que a conta foi criada com sucesso
		System.out.println(" Sua conta foi criada com sucesso!");

		// agora chamo meu metodo operacoes denovo para saber qual a proxima a��o que o usu�rio quer fazer
		operacoes();
			
	}
	// precisamos agora fazer metodo para contar uma conta com usu�rio digitar a conta
	// vamos criar o m�todo encontrarConta que recebe o numero que o usu�rio digitar 
	private static Conta encontrarConta(int numeroConta) {
	//tem que retornar alguma coisa, retornar como null pq se a conta exitir a gente vai retornar
	Conta conta = null;
	// aqui precisa saber se a conta existe vamos conferir a lista de contas da nossa agencia bancaria
	if(contasBancarias.size() > 0) { //chamo a contasBancaria.size esse tamo tem que ser maior que 0, precisa conter algo pra ser existente
		for(Conta c : contasBancarias) { 
			if(c.getNumeroConta() == numeroConta ) { // se essa conta dentro da conta bancaria foir igual ao numero da conta q usuario digitou
				conta = c; // inves de ser num ela vai receber conta c
			}
		
		 }
	}
	
		return conta;

}
	// m�todo depositar 
	public static void depositar() {
		System.out.println("N�mero da conta: ");
		int numeroConta = input.nextInt();  //dai vou armazenar esse valor usu�rio digitou
		
		// passo esse numero para a minha conta
		// chamo o metodo encontrarConta e passo o numero da conta que o usu�rio digitou para ele
		Conta conta = encontrarConta(numeroConta); // vai realizar a logica do m�todo encontrar conta pra ver se existe
		if(conta != null) {
			System.out.println("Qual valor deseja depositar ?");
			Double valorDeposito = input.nextDouble(); // se a conta existir vai pedir o numero para armazenar 
			conta.depositar(valorDeposito); // vai passar esse valor para o m�todo depositar 
			System.out.println("Valor depositado com sucesso!");
		}else { // se for valor positivo ele vai dizer a mensagem acima se for n�gativo menor que zero mensagem a baixo
			System.out.println("Conta n�o encontrada! ");
		}	
		
		operacoes(); //menu de op��o de usu�rios
	}
	
	// m�todo sacar parecido com o m�todo depositar	
	public static void sacar() {
		System.out.println("N�mero da conta: "); // pedindo pro usu�rio o numero de uma conta
		int numeroConta = input.nextInt();  // vou armazenar essa conta digitada 
		Conta conta = encontrarConta(numeroConta);  //vou ver se essa conta existe
		
		if(conta != null) { 	// se a conta existir 
			System.out.println("Qual valor deseja sacar ?"); 
			Double valorSaque = input.nextDouble(); //pergunto valor que vai sacar
			conta.sacar(valorSaque); // passo esse valor para o meu m�todo sacar, pra ver se o valor � >0 e se tem saldo se tiver subtrai do saldo
			//System.out.println("Valor sacado com sucesso!");
		}else { //se n�o ele vai dar mesagem abaixo
			System.out.println("Conta n�o encontrada! ");
		}	
		
		operacoes(); //menu de op��o de usu�rios
						
	}
	
	// m�todo trasnferir
	// preciso ter uma conta que vai enviar a transferencia e a conta que vai receber a transferencia 
	public static void transferir() {
		System.out.println("N�mero da conta do remetente: ");
		int numeroContaRemetente = input.nextInt(); // numeroCOntaRemetente armazena o valor digitado.
		//vou criar uma contaRemetente e ver se ela existe pra estar realizando tranasferencia 
		Conta contaRemetente = encontrarConta(numeroContaRemetente); // chama o m�todo encontrarConta e vou passar o (numeroContaRemetente)
		
		if(contaRemetente != null) { // se conta do remetente existir e for diferente de null
			System.out.println("N�mero da conta do destinat�rio: ");
			int numeroContaDestinatario = input.nextInt(); // armazeno esse numeroCOntaDestinatario 
			
			// chamo o m�todo encontrarConta para quem eu quero transferir existe
			Conta contaDestinatario = encontrarConta(numeroContaDestinatario);
			
			// se a conta de destinatario exixtir ou diferente de null
			if(contaDestinatario != null) {
				System.out.println("Valor da trasnferencia: "); // pergunto valor da transferencia
				Double valor = input.nextDouble(); // armazeno a informa��o
				
				// pego minha contaRemetente chamo o m�todo transferir, passo minha contaDestinaratio e o valor
				contaRemetente.transferir(contaDestinatario, valor);
			}else{
				System.out.println("A conta para dep�sito n�o foi encontrada");
				}
			}else{
				System.out.println("Conta para transfer�ncia n�o encontrada");
				
			}
					
		operacoes();
	}

	// m�todo ver/listar conta
	public static void listarContas() {
		if (contasBancarias.size() > 0) {  //se minha lista de contas bacarias for maior > 0 vou percorrer a lista
			for (Conta conta : contasBancarias) { //para cada conta dentro da minha lista de contaBancarias 
				System.out.println(conta);	// imprimo cada uma delas
			}

		} else {	// caso contrario informo que n tem contas cadastradas
			System.out.println("N�o h� contas cadastradas");
		}
		operacoes();
	}

}
	


