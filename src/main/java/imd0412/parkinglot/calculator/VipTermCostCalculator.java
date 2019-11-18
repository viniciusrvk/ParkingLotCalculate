package imd0412.parkinglot.calculator;

public class VipTermCostCalculator implements TermCostCalculator {

	@Override
	public Float calcular(long horasEstacionado) {
		
		Float cost = 500F;
		
		cost += adcionarTaxaDiaria100Reais(horasEstacionado);
		cost += adcionarTaxaDiaria80Reais(horasEstacionado);
	
		return cost;
	}

	private Float adcionarTaxaDiaria100Reais(long horasEstacionado) {
		Float extra = 0F;
		if (horasEstacionado > 168 && horasEstacionado <= 336) { 	// Adiciona a diaria de 50 rais a cada 24 horas (desconciderando as primeiras 24 horas)
			
			if ((horasEstacionado-168) > 24) {
				
				extra += (((horasEstacionado-168)/24)*100);
				
				if ((horasEstacionado-168)%24 > 0) {
					extra += 100;
				}
				
			} else {
				extra = 100F;
			}
			
			
		}
		return extra;
	}

	private Float adcionarTaxaDiaria80Reais(long horasEstacionado) {
		Float extra = 0F;
		
		if (horasEstacionado > 336)  {
			
			extra += (((168)/24)*100); // 7 dias de 100 rais
			horasEstacionado -= 336;
			
			extra += (((horasEstacionado)/24)*80);
			
			if ((horasEstacionado)%24 > 0) {
				extra += 80;
			}
			
		}
		
		return extra;
	}
	
	

}
