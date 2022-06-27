package Programa;

import utilitarios.Utils;

public class Conta {
	
	// n�o quero controlar um numero de contas , quero que isso seja controlado automaticamente
	// definindo contador ao criar conta sera atribuito um numero crescente 
	private static int contadorDeContas = 1;
	
	// essa conta tera numero da conta, pessoa e saldo inicial 0
	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	
	//nosso numero conta vai receber nosso contador de conta
	public Conta(Pessoa pessoa) {
		this.numeroConta = contadorDeContas;
		this.pessoa = pessoa;
	// aqui vamaos incrementar 	esse contador toda vez que for criada acrescenta um numero
		contadorDeContas +=1;
	 
		}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
		
	// vamos pegar os dados da classe pessoa
	public String toString() {
		return "\nN�mero da Conta: " + this.getNumeroConta()+
				"\nNome: " + this.pessoa.getNome()+
				"\nCPF: " + this.pessoa.getCpf()+
				"\nEmail: " + this.pessoa.getEmail()+
				"\nSaldo: " + Utils.doubleToString(this.getSaldo())+
				"\n";
		
	}
	
	// vamos implementar os metodos depositar, sacar, transferencia
	//pra conseguir depositar o numero tem que ser positivo, maior que zero.
	public void depositar(Double valor) {
		if(valor > 0) { // se valor for maior que zero
			setSaldo(getSaldo() + valor); // a gente chama setSaldo (getSaldo) e soma com o valor que ja vou ter se for zero soma + valor 
			System.out.println("Seu dep�sito foi realizado com sucesso!"); // se isso acontecer a gente avisa que o deposito foi realizado com sucesso
		}else { // caso contrario n�o foi possivel realizar o dep�sito
			System.out.println("N�o foi possivel realizar o dep�sito!");
			}
		}
	// Aqui preciso saber se valor que saco � maior que zero pq n pode sacar numero negativo
	// preciso saber se tenho esse saldo suficinete no caso valor positivo
	//M�todo Sacar
	public void sacar(Double valor) {
		if(valor > 0 && this.getSaldo()>= valor) { //se getSaldo for maior igual q o cliente quer sacar, sacar this.getsaldo
			setSaldo(getSaldo() - valor);	// ok deixa ele sacar getSaldo subtrair - valor da conta.
			System.out.println("Seu saque foi realizado com sucesso!");
		}else { // caso contrario n�o foi possivel realziar o saque
			System.out.println("N�o foi possivel realizar o saque!");	
			}
		} 
	
		// Aqui no transferir vc precisa de uma conta que vai transferir para deposito e um valor 
		// m�todo transferencia
	public void transferir(Conta contaParaDeposito, Double valor) { //aqui a gente recebe uma contaParaDeposito e recebo tb Double valor
		if(valor > 0 && this.getSaldo() >= valor) { //se getSaldo for >= q o cliente quer transferir, transferir this.getsaldo
			setSaldo(getSaldo() - valor); //setSaldo meu sado pego o getSaldo que � o q tem na minha conta, subtraio valor que trasnferir da conta 
		// A minha contaParaDeposito vai receber o meu valor que to transferindo pra ela
		//Aqui vai pegar o saldo que tem na conta e vai transferir chama contaParaDeposito.getSaldo + valor que estou transferindo 	
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor; 
			System.out.println("Transfer�ncia realizada com sucesso!");
		}else { // caso contrario n�o foi possivel realziar a transfer�ncia
			System.out.println("N�o foi possivel realizar o saque!");	
		}
	
	}	
		
}
	

