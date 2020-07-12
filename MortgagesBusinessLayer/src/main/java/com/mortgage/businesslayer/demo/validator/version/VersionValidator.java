package com.mortgage.businesslayer.demo.validator.version;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.impl.MortgageServiceIMPL;

public class VersionValidator implements ConstraintValidator<ValidateVersion, MortgageDto> {

	@Autowired
	private MortgageServiceIMPL service;

	/**
	 * valid method to check max version from date base with input version for given
	 * mortgage
	 * 
	 */
	@Override
	public boolean isValid(final MortgageDto mortgageDto, final ConstraintValidatorContext context) {
		int maxVersion;
		try {
			maxVersion = service.getMaxVersion(mortgageDto.getMortgageIDReq());
			if (mortgageDto.getVersionReq() < maxVersion) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						"Version Error, Input Version " + mortgageDto.getVersionReq() + " for Mortgage id "
								+ mortgageDto.getMortgageIDReq() + " is less than max version "+maxVersion+" available in Database")
						.addConstraintViolation();
				return false;
			}
			return true;
		} catch (MortgageBusinessException e) {
		}
		return false;
	}

	/**
	 * 
	 */
	public VersionValidator() {
		super();
	}

	/**
	 * @param service the service to set
	 */
	public void setService(MortgageServiceIMPL service) {
		this.service = service;
	}

}
