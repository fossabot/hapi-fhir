package ca.uhn.fhir.util;

/*-
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 - 2019 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.context.BaseRuntimeChildDefinition;
import ca.uhn.fhir.context.BaseRuntimeElementDefinition;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.RuntimeResourceDefinition;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.instance.model.api.IBaseBinary;
import org.hl7.fhir.instance.model.api.IBaseReference;

import java.util.List;

public class BinaryUtil {

	private BinaryUtil() {
		// non instantiable
	}

	public static IBaseReference getSecurityContext(FhirContext theCtx, IBaseBinary theBinary) {
		RuntimeResourceDefinition def = theCtx.getResourceDefinition("Binary");
		BaseRuntimeChildDefinition child = def.getChildByName("securityContext");
		IBaseReference retVal = null;
		if (child != null) {
			List<IBase> values = child.getAccessor().getValues(theBinary);
			if (values.size() > 0) {
				retVal = (IBaseReference) values.get(0);
			}
		}
		return retVal;
	}

	public static IBaseBinary newBinary(FhirContext theCtx) {
		return (IBaseBinary) theCtx.getResourceDefinition("Binary").newInstance();
	}

	public static void setSecurityContext(FhirContext theCtx, IBaseBinary theBinary, String theSecurityContext) {
		RuntimeResourceDefinition def = theCtx.getResourceDefinition("Binary");
		BaseRuntimeChildDefinition child = def.getChildByName("securityContext");

		BaseRuntimeElementDefinition<?> referenceDef = theCtx.getElementDefinition("reference");
		IBaseReference reference = (IBaseReference) referenceDef.newInstance();
		child.getMutator().addValue(theBinary, reference);

		reference.setReference(theSecurityContext);
	}

}
