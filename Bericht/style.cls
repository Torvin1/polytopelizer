%*******************************************************************************
%  style.cls
%
%  LaTeX-Dokumentenklasse fuer Seminararbeiten
%
%*******************************************************************************


\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{style}[]

% load class
\LoadClass[a4paper,11pt,oneside]{report}

% load packages
\RequirePackage[ngerman]{babel}
\RequirePackage[utf8]{inputenc}	
\RequirePackage[activate={true,nocompatibility}]{microtype}
\RequirePackage{tabularx}
\RequirePackage{amsfonts}
\RequirePackage{floatflt}
\RequirePackage[pdftex]{color,graphicx}
\RequirePackage{amssymb}
\RequirePackage{amsmath}
\RequirePackage{enumerate}
\RequirePackage{amsthm}
\RequirePackage{MnSymbol}
\RequirePackage{hangcaption}
\RequirePackage{algpseudocode}
\RequirePackage{algorithm}
\RequirePackage{times}
\RequirePackage{setspace}
\RequirePackage{listings}
\RequirePackage[]{caption}
\RequirePackage{hyperref}
\hypersetup{
	plainpages=false,
	linkcolor=blue,
	citecolor=blue,
	urlcolor=blue,
	breaklinks=true,
	colorlinks=true,
	citebordercolor=0 0 0,
	filebordercolor=0 0 0,
	linkbordercolor=0 0 0,
	menubordercolor=0 0 0,
	urlbordercolor=0 0 0,
	pdfhighlight=/I
}

% captions should be italic and bold
\renewcommand\captionfont{\small\itshape}
\renewcommand\captionlabelfont{\bf\upshape}

% create titlepage
\renewcommand{\maketitle}{
	\begin{titlepage}
	\begin{figure}[htbp]
		\begin{minipage}[b]{0.4\textwidth} 
		Institut für Informatik \\
		Freie Universität Berlin \\
		Takustraße 9 \\
		14195 Berlin
		\end{minipage}
		\hfill
		\begin{minipage}[b]{0.5\textwidth}
		\includegraphics[width=\textwidth]{./figures/FULogo.pdf} 
		\end{minipage}
	\end{figure}
	

	\begin{center}
	\vspace*{1.5cm}
	\vspace*{1.5cm}
	\vspace*{1.5cm}

			{\Large Abschlussbericht zum Softwareprojekt}\\[1.25cm]
			{\huge\LARGE\@title}\\[1.25cm] 
			{\@author}\\
			\vspace*{5cm}
			\normalsize 
			Softwareprojekt über Anwendungen von Algorithmen\\
			Dozent: Prof. Dr. Günter Rote\\
			Sommersemester 2014\\
			
	\end{center}
	\end{titlepage}
}

\newtheorem{thm}{Satz}[section]
\newtheorem{cor}[thm]{Korollar}
\newtheorem{lem}[thm]{Lemma}
\newtheorem{newdef}{Definition}[section]
\newtheorem{bemerk}{Bemerkung}[section]

\newcommand{\lsieht}{\leftrightarrow}
\newcommand{\rsieht}{\square}

\newcommand{\A}{\mathcal{A}}
\newcommand{\C}{\mathcal{C}}
\newcommand{\E}{\mathcal{E}}
\newcommand{\Y}{\mathcal{Y}}
\newcommand{\T}{\mathcal{T}}
\newcommand{\R}{\mathbb{R}}
\newcommand{\N}{\mathbb{N}}
\newcommand{\Oe}{\mathcal{O}}
\newcommand{\G}{\mathcal{G}}
\newcommand{\B}{\mathcal{B}}
\newcommand{\M}{\mathcal{M}}
\newcommand{\Pe}{\mathcal{P}}