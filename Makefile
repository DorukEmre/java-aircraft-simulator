SHELL		= /bin/sh

NAME		= avaj_launcher

SOURCES	= $(shell find * -name "*.java")
TARGET	= sources.txt
OUTPUT	= simulation.txt
BIN_DIR	= bin

all: compile run

compile:
	@printf "%s\n" $(SOURCES) > $(TARGET)
	@echo "$$(wc -l < $(TARGET)) files copied to $(TARGET)"
	@mkdir -p $(BIN_DIR)
	@echo "Compiling Java sources:"
	javac -d $(BIN_DIR) @sources.txt

run:
	@echo "Running the Java simulator:"
	java -cp bin demre.avaj.simulator.Simulator scenario.txt

clean:
	rm -f $(TARGET)
	rm -f $(OUTPUT)
	rm -rf $(BIN_DIR)
	find . -name "*.class" -type f -delete

re: clean all
