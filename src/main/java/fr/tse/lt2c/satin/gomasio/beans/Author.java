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
import java.util.Iterator;

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


@RdfsClass("foaf:Person")
@Entity
public class Author implements SupportsRdfId {
	/**
	 * Default support for the ID of an RDF concept
	 */
	private SupportsRdfId mIdSupport = new SupportsRdfIdImpl();

	@RdfProperty("swrc:affiliation")
	private Collection<URI> mAffiliation;

	/**
	 * Normally you should expect only one foaf:name per resource
	 * However some data in the semantic web dog food present duplicates ... Ex workshop FIRST 2007
	 * All aka are merge when calling getmName()
	 */
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:name")
	private Collection<String> mName;
	
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:family_name") // some authors have several family names ...
	private Collection<String> mFamilyName;
	
	@OneToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:firstName")
	private Collection<String> mFirstName;
	
	@OneToMany(fetch = FetchType.LAZY,
	   cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@RdfProperty("foaf:made")
	private Collection<URI> mPapers = new HashSet<URI>();
	

	public SupportsRdfId getmIdSupport() {
		return mIdSupport;
	}

	public Collection<URI> getmAffiliation() {
		return mAffiliation;
	}

	public Collection<String> getmFamilyName() {
		return mFamilyName;
	}

	
	public Collection<String> getmFirstName() {
		return mFirstName;
	}

	public Collection<URI> getmPapers() {
		return mPapers;
	}
	
	public Author()
	{
		this.mPapers = new ArrayList<URI>();
		mFirstName =  new ArrayList<String>();
		mFamilyName =  new ArrayList<String>();
		mAffiliation = new ArrayList<URI>();
		mPapers =  new ArrayList<URI>();
		mName =  new ArrayList<String>();
	}
	
	public Author(String surName, String familyName)
	{
		mFirstName =  new ArrayList<String>();
		mFamilyName =  new ArrayList<String>();
		mAffiliation = new ArrayList<URI>();
		mPapers =  new ArrayList<URI>();
		mName =  new ArrayList<String>();
		
		mName.add(surName + " "+ familyName);
		mFirstName.add(surName);
		mFamilyName.add(familyName);
	}


public String getmName() {
		String name = null;
		Iterator<String> names = mName.iterator();
		boolean first = true;
		boolean second = true;
		while (names.hasNext())
		{
			if (first)
			{
				name = names.next();
				first = false;
			}
			else
			{
				if (second)
				{
					name += " (aka "+names.next()+")";
					second = false;
				}
				else
				{
					name = name.replace(")", "");
					name += ", "+names.next()+")";
				}
			}
		}
		return name;
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
	
	public void setmPaper(Collection<URI> c)
	{
		this.mPapers=c;
	}
}
