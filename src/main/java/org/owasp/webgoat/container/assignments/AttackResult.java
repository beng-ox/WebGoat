/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details,
 * please see http://www.owasp.org/
 * <p>
 * Copyright (c) 2002 - 2017 Bruce Mayhew
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 * <p>
 * Getting Source ==============
 * <p>
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software
 * projects.
 * <p>
 */

package org.owasp.webgoat.container.assignments;

import static org.apache.commons.text.StringEscapeUtils.escapeJson;

import lombok.Getter;
import org.owasp.webgoat.container.i18n.PluginMessages;

@Getter
public class AttackResult {

  private boolean lessonCompleted;
  private String feedback;
  private Object[] feedbackArgs;
  private String output;
  private Object[] outputArgs;
  private final String assignment;
  private boolean attemptWasMade;

  private AttackResult(
      boolean lessonCompleted,
      String feedback,
      String output,
      String assignment,
      boolean attemptWasMade) {
    this.lessonCompleted = lessonCompleted;
    this.feedback = escapeJson(feedback);
    this.output = escapeJson(output);
    this.assignment = assignment;
    this.attemptWasMade = attemptWasMade;
  }

  public AttackResult(
      boolean lessonCompleted,
      String feedback,
      Object[] feedbackArgs,
      String output,
      Object[] outputArgs,
      String assignment,
      boolean attemptWasMade) {
    this.lessonCompleted = lessonCompleted;
    this.feedback = feedback;
    this.feedbackArgs = feedbackArgs;
    this.output = output;
    this.outputArgs = outputArgs;
    this.assignment = assignment;
    this.attemptWasMade = attemptWasMade;
  }

  public boolean assignmentSolved() {
    return lessonCompleted;
  }

  public AttackResult apply(PluginMessages pluginMessages) {
    return new AttackResult(
        lessonCompleted,
        pluginMessages.getMessage(feedback, feedback, feedbackArgs),
        pluginMessages.getMessage(output, output, outputArgs),
        assignment,
        attemptWasMade);
  }
}
