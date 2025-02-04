/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.repository.content.search.table;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.search.index.SearchUpdateService;
import org.sonatype.nexus.repository.types.GroupType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Determines if a SQL table search index should be updated with common {@link SearchUpdateService} logic.
 */
@Named
@Singleton
public class SqlTableSearchUpdateService
    implements SearchUpdateService
{
  private final SearchTableStore searchTableStore;

  @Inject
  public SqlTableSearchUpdateService(final SearchTableStore searchTableStore) {
    this.searchTableStore = checkNotNull(searchTableStore);
  }

  @Override
  public boolean needsReindex(final Repository repository) {
    if (GroupType.NAME.equals(repository.getType().getValue())) {
      return false;
    }
    return searchTableStore.repositoryNeedsReindex(repository.getName());
  }

  @Override
  public void doneReindexing(final Repository repository) {
    // . . .
  }
}
