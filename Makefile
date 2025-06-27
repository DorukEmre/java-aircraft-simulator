SHELL		= /bin/sh

SOURCES	= $(shell find * -name "*.java")
TARGET	= sources.txt
OUTPUT	= simulation.txt
BIN_DIR	= bin

all: compile run

compile	:
	@printf "%s\n" $(SOURCES) > $(TARGET)
	@echo "$$(wc -l < $(TARGET)) files copied to $(TARGET) file"
	@mkdir -p $(BIN_DIR)
	@echo "Compiling Java sources:"
	javac -d $(BIN_DIR) @$(TARGET)

run			:
	@echo "Running the Java simulator:"
	java -cp $(BIN_DIR) aircraft.simulator.Simulator scenario.txt

clean		:
	rm -f $(TARGET)
	rm -rf $(BIN_DIR)
	rm -f $(OUTPUT)
	find . -name "*.class" -type f -delete

re			: clean all

.PHONY	: all compile run clean re
.DEFAULT_GOAL := all
