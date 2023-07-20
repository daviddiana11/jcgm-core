/*
 * <copyright> Copyright 1997-2003 BBNT Solutions, LLC under sponsorship of the
 * Defense Advanced Research Projects Agency (DARPA).
 * Copyright 2009 Swiss AviationSoftware Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the Cougaar Open Source License as published by DARPA on
 * the Cougaar Open Source Website (www.cougaar.org).
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sf.jcgm.core;

/**
 * Message generated by the CGM interpreter for unimplemented or unsupported features.
 * @author xphc (Philippe Cadé)
 * @version $Id$
 */
public class Message {
	private final Severity severity;
	private final String message;
	private final int elementClass;
	private final int elementId;
	private final String commandDescription;

	public enum Severity { INFO, UNSUPPORTED, UNIMPLEMENTED, FATAL }

	/**
	 * Builds a message
	 * 
	 * @param severity
	 *            The severity of the message
	 * @param elementClass
	 *            The corresponding element class
	 * @param elementCode
	 *            The corresponding element ID
	 * @param message
	 *            A message
	 * @param commandDescription
	 *            The command description (optional), typically the output of
	 *            the <code>toString()</code> method for the command
	 */
	public Message(Severity severity, int elementClass, int elementCode, String message, String commandDescription) {
		this.severity = severity;
		this.elementClass = elementClass;
		this.elementId = elementCode;
		this.message = message;
		this.commandDescription = commandDescription;
	}

	public final int getElementClass() {
		return this.elementClass;
	}

	public final int getElementCode() {
		return this.elementId;
	}

	public static Message info(Command cmd, String message) {
		return new Message(Message.Severity.INFO, cmd.getElementClass(), cmd.getElementCode(), message, cmd.toString());
	}
	
	public static Message cmdUnsupported(int elementClass, int elementCode, String cmdDescription) {
		return cmdUnsupported(elementClass, elementCode, "unsupported", cmdDescription);
	}
	
	public static Message cmdUnsupported(int elementClass, int elementCode, String message, String cmdDescription) {
		return new Message(Severity.UNSUPPORTED, elementClass, elementCode, message, cmdDescription);
	}
	
	public static Message unimplemented(Command cmd, String message) {
		return new Message(Severity.UNIMPLEMENTED, cmd.getElementClass(), cmd.getElementCode(), message, cmd.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ElementClass.getElementClass(this.elementClass)).append(" ");
		sb.append(ElementClass.getElement(this.elementClass, this.elementId)).append(" ");
		sb.append(this.severity).append(" ").append(this.message);
		if (this.commandDescription != null) {
			sb.append(" {").append(this.commandDescription).append("}");
		}
		return sb.toString();
	}
}

/*
 * vim:encoding=utf8
 */
