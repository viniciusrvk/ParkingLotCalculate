package imd0412.parkinglot.calculator;

public class ShortTermCostCalculator implements TermCostCalculator {

	@Override
	public Float calcular(long horasEstacionado) {
		
		Float cost = 8F;
		
		cost += adicionarHoraExtra(horasEstacionado);
		cost += adcionarTaxaDiaria50Reais(horasEstacionado);
		cost += adiconarTaxaDiaria30Reais(horasEstacionado);
		
		return cost;
	}

	private Float adiconarTaxaDiaria30Reais(long horasEstacionado) {
		Float extra = 0F;
		
		if (horasEstacionado > 168) {
			extra += (((168)/24)*50);		// Adicona a taxa de 50 por 7 dias de estacionamento
			
			horasEstacionado -= 168;		// Desconta os 7 dias do tempo estacionado
			
			if (horasEstacionado >= 24) {
				extra += (((horasEstacionado)/24)*30);
			}
		}
		return extra;
	}

	private Float adcionarTaxaDiaria50Reais(long horasEstacionado) {
		Float extra = 0F;
		if (horasEstacionado > 24 && horasEstacionado <= 168) { 	// Adiciona a diaria de 50 rais a cada 24 horas (desconciderando as primeiras 24 horas)
			extra += (((horasEstacionado)/24)*50);
		}
		return extra;
	}

	private Float adicionarHoraExtra(long horasEstacionado) {
		Float extra = 0F;
		if (horasEstacionado > 1) {		// Soma 2 reais a cada horas extra
			extra += ((horasEstacionado-1)*2);
		}
		return extra;
	}
	
	

}
