package fr.tse.lt2c.satin.gomasio.beans;

/*
 * Copyright (c) 20012 Telecom Saint-Etienne <http://www.telecom-st-etienne.fr>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.net.URI;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.SupportsRdfId;
import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;
import com.clarkparsia.empire.annotation.SupportsRdfIdImpl;

/**
 * <p>Author class for authors in the <a href="http://data.semanticweb.org/" >Semantic Web Dog Food corpus</a></p>
 *
 * @author Christophe Gravier (@chgravier), following example provided by Michael Grove in Empire source code.
 */
@Namespaces({"rdf", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>",
"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
"owl","http://www.w3.org/2002/07/owl#",
"dc", "http://purl.org/dc/elements/1.1/",
"foaf", "http://xmlns.com/foaf/0.1/",
"swrc", "http://swrc.ontoware.org/ontology#",
"swrc-ext", "http://www.cs.vu.nl/~mcaklein/onto/swrc_ext/2005/05#",
"geo", "http://www.w3.org/2003/01/geo/wgs84_pos#",
"ical", "http://www.w3.org/2002/12/cal/ical#",
"swc", "http://data.semanticweb.org/ns/swc/ontology#"})


@RdfsClass("foaf:Organization")
@Entity
public class Organization implements SupportsRdfId {
	/**
	 * Default support for the ID of an RDF concept
	 */
	private SupportsRdfId mIdSupport = new SupportsRdfIdImpl();

	@RdfProperty("foaf:name")
	private String mName;


	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:member")
	private Collection<URI> mMembers;
	
	
	public SupportsRdfId getmIdSupport() {
		return mIdSupport;
	}


public String getmName() {
		return mName;
	}


	public Collection<URI> getmMembers() {
		return mMembers;
	}


/**
 * TODO Write this methods as the getters list no longer evolve as much as it now
	@Override
	public boolean equals(final Object theObj) {
		if (this == theObj) {
			return true;
		}
		if (theObj == null || !(theObj instanceof Book)) {
			return false;
		}

		final Book aBook = (Book) theObj;

		if (getRdfId() != null) {
			return getRdfId().equals( aBook.getRdfId() );
		}
		else {
			if (mEmbodiments != null ? !mEmbodiments.equals(aBook.mEmbodiments) : aBook.mEmbodiments != null) {
				return false;
			}
			if (mIssued != null ? !mIssued.equals(aBook.mIssued) : aBook.mIssued != null) {
				return false;
			}
			if (mPrimarySubjectOf != null ? !mPrimarySubjectOf.equals(aBook.mPrimarySubjectOf) : aBook.mPrimarySubjectOf != null) {
				return false;
			}
			if (mPublisher != null ? !mPublisher.equals(aBook.mPublisher) : aBook.mPublisher != null) {
				return false;
			}
			if (mTitle != null ? !mTitle.equals(aBook.mTitle) : aBook.mTitle != null) {
				return false;
			}
		}

		return true;
	}*/

	@Override
	public int hashCode() {
		return getRdfId() == null ? 0 : getRdfId().value().hashCode();
	}

	/**
	 * @inheritDoc
	 */
	public RdfKey getRdfId() {
		return mIdSupport.getRdfId();
	}

	/**
	 * @inheritDoc
	 */
	public void setRdfId(final RdfKey theId) {
		mIdSupport.setRdfId(theId);
	}
}
