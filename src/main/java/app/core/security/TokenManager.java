package app.core.security;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.core.exeptions.CouponSystemExceptions;

@Service
public class TokenManager {
	private Map<UUID, Information> tokens = new HashMap<>();

	private void deleteToken(int id) {
		tokens.entrySet().removeIf((token) -> token.getValue().getId() == id);
	}

	public UUID addToken(Information information) {
		deleteToken(information.getId());
		UUID token = UUID.randomUUID();
		tokens.put(token, information);
		return token;

	}

	private int getUserId(UUID token, ClientType clientType) throws CouponSystemExceptions {
		Information information = tokens.get(token);
		if (information == null) {
			throw new CouponSystemExceptions("token is not valid");
		}
		if (information.getClientType() != clientType) {
			throw new CouponSystemExceptions("unauthorized action");
		}
		return information.getId();
	}
	public int getAdminId(UUID token) throws CouponSystemExceptions {
		return getUserId(token, ClientType.ADMINISTRATOR);
		
	}
	public int getCompanyId(UUID token) throws CouponSystemExceptions {
		return getUserId(token, ClientType.COMPANY);
		
	}

	public int getCustomerid(UUID token) throws CouponSystemExceptions {
		return getUserId(token, ClientType.CUSTOMER);
	}
	@Scheduled(fixedRate = 1000*60)
	public void deleteExpiredTokens() {
		tokens.entrySet().removeIf((token) -> token.getValue().getExpertionTime().isBefore(LocalDateTime.now()));
	}
	
}
