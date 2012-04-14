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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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


@RdfsClass("swrc:InProceedings")
@Entity
public class Paper implements SupportsRdfId {
	/**
	 * Default support for the ID of an RDF concept
	 */
	private SupportsRdfId mIdSupport = new SupportsRdfIdImpl();
	/*
	@RdfProperty("rdfs:id")
	protected String mId;
	*/
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("swc:isPartOf")
	private Collection<URI> mEvent;
	
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("swc:relatedToEvent")
	private Collection<URI> mSubEvent;
	
	@RdfProperty("dc:title")
	private String mTitle;
	

	@RdfProperty("swrc:pages")
	private String mPages;
	
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("swrc:url")
	private Collection<URI> mUrl;
	
	@RdfProperty("rdfs:label")
	private Collection<String> mAlternativeTitle;
	
	@RdfProperty("swrc:abstract")
	private String mAbstract;
	
	@RdfProperty("swrc:year")
	private String mPublishYear;

	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:maker")
	private Collection<URI> mAuthors = new HashSet<URI>();
	
	public Paper()
	{}
	
	public Paper(String t, String a, String y, String p)
	{
		this.mTitle=t;
		this.mAbstract=a;
		this.mPublishYear=y;
		this.mPages=p;
		this.mAuthors = new ArrayList<URI>();
		this.mEvent = new ArrayList<URI>();
	}
	
	public Collection<URI> getmAuthors() {
		return mAuthors;
	}

	public Collection<URI> getmEvent() {
		return mEvent;
	}

	public String getmTitle() {
		return mTitle;
	}

	public String getmPublishYear() {
		return mPublishYear;
	}

	public SupportsRdfId getmIdSupport() {
		return mIdSupport;
	}

	

public String getmAbstract() {
		return mAbstract;
	}


public Collection<String> getmAlternativeTitleCollection() {
	return mAlternativeTitle;
}

public String getmAlternativeTitle() {
	String alt = "";
	for (String s : mAlternativeTitle)
	{
		alt += s;
	}
	return alt;
}

public void setmAlternativeTitle(Collection<String> mAlternativeTitle) {
	this.mAlternativeTitle = mAlternativeTitle;
}

public void addmAlternativeTitle(String mAlternativeTitle) {
	this.mAlternativeTitle.add(mAlternativeTitle);
}

public Collection<URI> getmUrl() {
	return mUrl;
}

public void setmUrl(Collection<URI> mUrl) {
	this.mUrl = mUrl;
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

	public Collection<URI> getmSubEvent() {
		return mSubEvent;
	}

	public void setmSubEvent(Collection<URI> mSubEvent) {
		this.mSubEvent = mSubEvent;
	}
	
	
}
