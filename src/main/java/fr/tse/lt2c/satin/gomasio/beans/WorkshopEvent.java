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
import java.util.Date;

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
 * <p>
 * Author class for authors in the <a href="http://data.semanticweb.org/"
 * >Semantic Web Dog Food corpus</a>
 * </p>
 * 
 * @author Christophe Gravier (@chgravier), following example provided by
 *         Michael Grove in Empire source code.
 *         TODO : how to use heritage between WorkshopEvent and ConferenceEvent (tried to factorized in an Abstract Event class, but got issues with RDFAnnotation inheritance ...)
 */
@Namespaces({ "rdf", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>", "rdfs",
		"http://purl.org/dc/elements/1.1/", "foaf",
		"http://xmlns.com/foaf/0.1/", "swrc",
		"http://www.w3.org/2003/01/geo/wgs84_pos#", "ical",
		"http://www.w3.org/2002/12/cal/ical#", "swc",
		"http://data.semanticweb.org/ns/swc/ontology#" })

@RdfsClass("swc:WorkshopEvent")
@Entity
public class WorkshopEvent implements SupportsRdfId {
	/**
	 * Default support for the ID of an RDF concept
	 */
	private SupportsRdfId mIdSupport = new SupportsRdfIdImpl();

	@RdfProperty("swc:hasLocation")
	private URI mLocation;

	@RdfProperty("swc:hasLogo")
	private URI mLogo;

	@RdfProperty("swc:hasProgramme")
	private URI mProgramme;

	@RdfProperty("swc:hasAcronym")
	protected String mAcronym;

	@RdfProperty("rdfs:label")
	protected String mNom;

	@RdfProperty("rdfs:comment")
	protected String mDescription;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@RdfProperty("swc:hasRelatedDocument")
	protected Collection<URI> mRelatedDocuments;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@RdfProperty("swc:isSubEventOf")
	protected Collection<URI> mSubEventOf;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@RdfProperty("swc:isSuperEventOf")
	protected Collection<URI> mSuperEventOf;
	
	@RdfProperty("ical:dtend")
	protected Date mBegin;

	@RdfProperty("ical:dtstart")
	protected Date mEnd;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@RdfProperty("ical:url")
	protected Collection<URI> mLinks;

	public SupportsRdfId getmIdSupport() {
		return mIdSupport;
	}

	public String getmAcronym() {
		return mAcronym;
	}

	public String getmNom() {
		return mNom;
	}

	public Collection<URI> getmRelatedDocuments() {
		return mRelatedDocuments;
	}

	public Collection<URI> getmSubEventOf() {
		return mSubEventOf;
	}
	public Collection<URI> getmSuperEventOf() {
		return mSuperEventOf;
	}

	public Date getmBegin() {
		return mBegin;
	}

	public Date getmEnd() {
		return mEnd;
	}

	public Collection<URI> getmLinks() {
		return mLinks;
	}

	public String getmDescription() {
		return mDescription;
	}

	
	
	public URI getmLocation() {
		return mLocation;
	}

	public URI getmLogo() {
		return mLogo;
	}

	public URI getmProgramme() {
		return mProgramme;
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
